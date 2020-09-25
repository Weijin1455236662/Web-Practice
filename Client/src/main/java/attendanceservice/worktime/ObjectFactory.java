
package attendanceservice.worktime;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the attendanceservice.worktime package. 
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

    private final static QName _GetAllWorkTimeResponse_QNAME = new QName("http://WorkTime.AttendanceService/", "getAllWorkTimeResponse");
    private final static QName _GetAllWorkTime_QNAME = new QName("http://WorkTime.AttendanceService/", "getAllWorkTime");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: attendanceservice.worktime
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllWorkTimeResponse }
     * 
     */
    public GetAllWorkTimeResponse createGetAllWorkTimeResponse() {
        return new GetAllWorkTimeResponse();
    }

    /**
     * Create an instance of {@link GetAllWorkTime }
     * 
     */
    public GetAllWorkTime createGetAllWorkTime() {
        return new GetAllWorkTime();
    }

    /**
     * Create an instance of {@link WorkTimeItem }
     * 
     */
    public WorkTimeItem createWorkTimeItem() {
        return new WorkTimeItem();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllWorkTimeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WorkTime.AttendanceService/", name = "getAllWorkTimeResponse")
    public JAXBElement<GetAllWorkTimeResponse> createGetAllWorkTimeResponse(GetAllWorkTimeResponse value) {
        return new JAXBElement<GetAllWorkTimeResponse>(_GetAllWorkTimeResponse_QNAME, GetAllWorkTimeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllWorkTime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WorkTime.AttendanceService/", name = "getAllWorkTime")
    public JAXBElement<GetAllWorkTime> createGetAllWorkTime(GetAllWorkTime value) {
        return new JAXBElement<GetAllWorkTime>(_GetAllWorkTime_QNAME, GetAllWorkTime.class, null, value);
    }

}
