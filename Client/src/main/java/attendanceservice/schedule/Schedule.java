
package attendanceservice.schedule;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Schedule", targetNamespace = "http://Schedule.AttendanceService/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Schedule {


    /**
     * 
     * @return
     *     returns java.util.List<attendanceservice.schedule.ScheduleItem>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllSchedule", targetNamespace = "http://Schedule.AttendanceService/", className = "attendanceservice.schedule.GetAllSchedule")
    @ResponseWrapper(localName = "getAllScheduleResponse", targetNamespace = "http://Schedule.AttendanceService/", className = "attendanceservice.schedule.GetAllScheduleResponse")
    @Action(input = "http://Schedule.AttendanceService/Schedule/getAllScheduleRequest", output = "http://Schedule.AttendanceService/Schedule/getAllScheduleResponse")
    public List<ScheduleItem> getAllSchedule();

}
