import attendanceservice.schedule.Schedule;
import attendanceservice.schedule.ScheduleItem;
import attendanceservice.schedule.ScheduleService;
import attendanceservice.worktime.WorkTime;
import attendanceservice.worktime.WorkTimeItem;
import attendanceservice.worktime.WorkTimeService;

import java.util.List;

public class TestAttendance {
    public static void main(String[] args) {
        ScheduleService scheduleService = new ScheduleService();
        Schedule schedulePort = scheduleService.getSchedulePort();
        List<ScheduleItem> scheduleItems = schedulePort.getAllSchedule();
        for (ScheduleItem scheduleItem: scheduleItems){
            System.out.println(scheduleItem.getName() + " \t在每周 " + scheduleItem.getWorkDay() + " 的 " + scheduleItem.getWorkTime() + " 工作");
        }

        WorkTimeService workTimeService = new WorkTimeService();
        WorkTime workTimePort = workTimeService.getWorkTimePort();
        List<WorkTimeItem> workTimeItems = workTimePort.getAllWorkTime();
        for (WorkTimeItem workTimeItem: workTimeItems){
            System.out.println(workTimeItem.getCode() + " 的工作时间段为 " + workTimeItem.getTime());
        }
    }
}
