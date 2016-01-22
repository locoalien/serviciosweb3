package webservice;

import java.util.Iterator;

import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SAAJResult;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPFault;
import javax.xml.transform.dom.DOMSource;

import webservice.jaxws.CreateUser;
import webservice.jaxws.GetUser;

public class UserSoapHandler {
	
	private static final String NAME_SPACE_URI = "http://webservice/";
	private static final QName CREATE_USER_QNAME = new QName(NAME_SPACE_URI, "createUser");
	private static final QName GET_USER_QNAME = new QName(NAME_SPACE_URI, "getUser");
	private MessageFactory messageFactory;
	private CreateUserAdapter greeterAdapter;
	
	public UserSoapHandler() throws SOAPException{
		messageFactory = MessageFactory.newInstance();
		greeterAdapter = new CreateUserAdapter();
	}
	@SuppressWarnings("rawtypes")
	public SOAPMessage handleSOAPRequest(SOAPMessage request) throws SOAPException{
		SOAPBody soapBody = request.getSOAPBody();
		Iterator iterator = soapBody.getChildElements();
		Object responsePojo = null;
		while(iterator.hasNext()){
			Object next = iterator.next();
			if(next instanceof SOAPElement){
				SOAPElement soapElement = (SOAPElement) next;
				QName qname = soapElement.getElementQName();
				
				if(CREATE_USER_QNAME.equals(qname)){
					responsePojo = handleCreateUser(soapElement);	
					break;
				}
				if(GET_USER_QNAME.equals(qname)){
					responsePojo = handleGetUser(soapElement);
					break;
				}
			
			}
		}
		SOAPMessage soapResponse = messageFactory.createMessage();
		soapBody = soapResponse.getSOAPBody();
		if(responsePojo != null){
			JAXB.marshal(responsePojo, new SAAJResult(soapBody));
		}else{
			SOAPFault fault = soapBody.addFault();
			fault.setFaultString("No se reconoce SOAP reponse");
		}
		return soapResponse;
	}
	
	private Object handleCreateUser(SOAPElement soapElement){
		CreateUser createuser = JAXB.unmarshal(new DOMSource(soapElement),  CreateUser.class);
		return greeterAdapter.createUser(createuser);
	}
	private Object handleGetUser(SOAPElement soapElement){
		GetUser getuser = JAXB.unmarshal(new DOMSource(soapElement), GetUser.class);
		return greeterAdapter.getUser(getuser);
	}

}
