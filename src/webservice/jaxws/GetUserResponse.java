
package webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getUserResponse", namespace = "http://webservice/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUserResponse", namespace = "http://webservice/")
public class GetUserResponse {

    @XmlElement(name = "return", namespace = "")
    private webservice.UserPojo _return;

    /**
     * 
     * @return
     *     returns UserPojo
     */
    public webservice.UserPojo getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(webservice.UserPojo _return) {
        this._return = _return;
    }

}
