
package com.proto.service.client.attendanceservice.schedule;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the attendanceservice.schedule package. 
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

    private final static QName _GetAllSchedule_QNAME = new QName("http://Schedule.AttendanceService/", "getAllSchedule");
    private final static QName _GetAllScheduleResponse_QNAME = new QName("http://Schedule.AttendanceService/", "getAllScheduleResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: attendanceservice.schedule
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllScheduleResponse }
     * 
     */
    public GetAllScheduleResponse createGetAllScheduleResponse() {
        return new GetAllScheduleResponse();
    }

    /**
     * Create an instance of {@link GetAllSchedule }
     * 
     */
    public GetAllSchedule createGetAllSchedule() {
        return new GetAllSchedule();
    }

    /**
     * Create an instance of {@link ScheduleItem }
     * 
     */
    public ScheduleItem createScheduleItem() {
        return new ScheduleItem();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllSchedule }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Schedule.AttendanceService/", name = "getAllSchedule")
    public JAXBElement<GetAllSchedule> createGetAllSchedule(GetAllSchedule value) {
        return new JAXBElement<GetAllSchedule>(_GetAllSchedule_QNAME, GetAllSchedule.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllScheduleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Schedule.AttendanceService/", name = "getAllScheduleResponse")
    public JAXBElement<GetAllScheduleResponse> createGetAllScheduleResponse(GetAllScheduleResponse value) {
        return new JAXBElement<GetAllScheduleResponse>(_GetAllScheduleResponse_QNAME, GetAllScheduleResponse.class, null, value);
    }

}
