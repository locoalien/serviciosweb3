package webservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;


import javax.servlet.http.*;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

@SuppressWarnings("serial")
public class Serviciosweb3Servlet extends HttpServlet {
	//public static final String URL = "http://ms4models.appspot.com/service";
	 
    private static MessageFactory messageFactory;
    private static UserSoapHandler soapHandler;
 
    static {
        try {
            messageFactory = MessageFactory.newInstance();
            soapHandler = new UserSoapHandler();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
 
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        try {
            // Get all the headers from the HTTP request
            MimeHeaders headers = getHeaders(req);
 
            // Construct a SOAPMessage from the XML in the request body
            InputStream is = req.getInputStream();
            SOAPMessage soapRequest = messageFactory.createMessage(headers, is);
 
          /*  ByteArrayOutputStream out = new ByteArrayOutputStream();
            soapRequest.writeTo(out);
            String strMsg = new String(out.toByteArray());*/
  
            // Handle soapReqest
            SOAPMessage soapResponse = soapHandler.handleSOAPRequest(soapRequest);
 
            // Write to HttpServeltResponse
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("text/xml;charset=\"utf-8\"");
            OutputStream os = resp.getOutputStream();
            soapResponse.writeTo(os);
            os.flush();
        } catch (SOAPException e) {
            throw new IOException("Exception while creating SOAP message.", e);
        }

    }
 
    @SuppressWarnings("rawtypes")
    static MimeHeaders getHeaders(HttpServletRequest req) {
        Enumeration headerNames = req.getHeaderNames();
        MimeHeaders headers = new MimeHeaders();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            String headerValue = req.getHeader(headerName);
            StringTokenizer values = new StringTokenizer(headerValue, ",");
            while (values.hasMoreTokens()) {
                headers.addHeader(headerName, values.nextToken().trim());
            }
        }
        return headers;
    }
}
