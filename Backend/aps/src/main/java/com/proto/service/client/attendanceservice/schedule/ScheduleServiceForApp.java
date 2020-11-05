package com.proto.service.client.attendanceservice.schedule;

import java.util.List;

public class ScheduleServiceForApp {
    private Schedule schedule;

    public ScheduleServiceForApp() {
        ScheduleService ss=new ScheduleService();
        this.schedule = ss.getSchedulePort();
    }

    public List<ScheduleItem> getAllSchedule(){return schedule.getAllSchedule();}

    public ScheduleItem findScheduleByName(String name){
        List<ScheduleItem> list=schedule.getAllSchedule();
        ScheduleItem scheduleItem=null;
        for (ScheduleItem item:list){
            if (item.name.equals(name)){
                scheduleItem=item;
                break;
            }
        }
        return scheduleItem;
    }
}
