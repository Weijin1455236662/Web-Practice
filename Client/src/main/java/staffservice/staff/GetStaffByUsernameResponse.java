
package staffservice.staff;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getStaffByUsernameResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getStaffByUsernameResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://Staff.StaffService/}staffIItem" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getStaffByUsernameResponse", propOrder = {
    "_return"
})
public class GetStaffByUsernameResponse {

    @XmlElement(name = "return")
    protected StaffIItem _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link StaffIItem }
     *     
     */
    public StaffIItem getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link StaffIItem }
     *     
     */
    public void setReturn(StaffIItem value) {
        this._return = value;
    }

}
