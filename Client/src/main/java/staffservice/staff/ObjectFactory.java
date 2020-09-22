
package staffservice.staff;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the staffservice.staff package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Verify_QNAME = new QName("http://Staff.StaffService/", "Verify");
    private final static QName _VerifyResponse_QNAME = new QName("http://Staff.StaffService/", "VerifyResponse");
    private final static QName _GetAllStaff_QNAME = new QName("http://Staff.StaffService/", "getAllStaff");
    private final static QName _GetAllStaffResponse_QNAME = new QName("http://Staff.StaffService/", "getAllStaffResponse");
    private final static QName _GetStaffByUsername_QNAME = new QName("http://Staff.StaffService/", "getStaffByUsername");
    private final static QName _GetStaffByUsernameResponse_QNAME = new QName("http://Staff.StaffService/", "getStaffByUsernameResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: staffservice.staff
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Verify }
     * 
     */
    public Verify createVerify() {
        return new Verify();
    }

    /**
     * Create an instance of {@link VerifyResponse }
     * 
     */
    public VerifyResponse createVerifyResponse() {
        return new VerifyResponse();
    }

    /**
     * Create an instance of {@link GetAllStaff }
     * 
     */
    public GetAllStaff createGetAllStaff() {
        return new GetAllStaff();
    }

    /**
     * Create an instance of {@link GetAllStaffResponse }
     * 
     */
    public GetAllStaffResponse createGetAllStaffResponse() {
        return new GetAllStaffResponse();
    }

    /**
     * Create an instance of {@link GetStaffByUsername }
     * 
     */
    public GetStaffByUsername createGetStaffByUsername() {
        return new GetStaffByUsername();
    }

    /**
     * Create an instance of {@link GetStaffByUsernameResponse }
     * 
     */
    public GetStaffByUsernameResponse createGetStaffByUsernameResponse() {
        return new GetStaffByUsernameResponse();
    }

    /**
     * Create an instance of {@link StaffIItem }
     * 
     */
    public StaffIItem createStaffIItem() {
        return new StaffIItem();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Verify }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Verify }{@code >}
     */
    @XmlElementDecl(namespace = "http://Staff.StaffService/", name = "Verify")
    public JAXBElement<Verify> createVerify(Verify value) {
        return new JAXBElement<Verify>(_Verify_QNAME, Verify.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link VerifyResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://Staff.StaffService/", name = "VerifyResponse")
    public JAXBElement<VerifyResponse> createVerifyResponse(VerifyResponse value) {
        return new JAXBElement<VerifyResponse>(_VerifyResponse_QNAME, VerifyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStaff }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllStaff }{@code >}
     */
    @XmlElementDecl(namespace = "http://Staff.StaffService/", name = "getAllStaff")
    public JAXBElement<GetAllStaff> createGetAllStaff(GetAllStaff value) {
        return new JAXBElement<GetAllStaff>(_GetAllStaff_QNAME, GetAllStaff.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStaffResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllStaffResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://Staff.StaffService/", name = "getAllStaffResponse")
    public JAXBElement<GetAllStaffResponse> createGetAllStaffResponse(GetAllStaffResponse value) {
        return new JAXBElement<GetAllStaffResponse>(_GetAllStaffResponse_QNAME, GetAllStaffResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStaffByUsername }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStaffByUsername }{@code >}
     */
    @XmlElementDecl(namespace = "http://Staff.StaffService/", name = "getStaffByUsername")
    public JAXBElement<GetStaffByUsername> createGetStaffByUsername(GetStaffByUsername value) {
        return new JAXBElement<GetStaffByUsername>(_GetStaffByUsername_QNAME, GetStaffByUsername.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStaffByUsernameResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStaffByUsernameResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://Staff.StaffService/", name = "getStaffByUsernameResponse")
    public JAXBElement<GetStaffByUsernameResponse> createGetStaffByUsernameResponse(GetStaffByUsernameResponse value) {
        return new JAXBElement<GetStaffByUsernameResponse>(_GetStaffByUsernameResponse_QNAME, GetStaffByUsernameResponse.class, null, value);
    }

}
