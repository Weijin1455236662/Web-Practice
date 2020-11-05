package com.proto.service.client.attendanceservice.worktime;

import java.util.List;

public class WorkTimeServiceForApp {
    private WorkTime workTime;

    public WorkTimeServiceForApp() {
        WorkTimeService wt=new WorkTimeService();
        this.workTime = wt.getWorkTimePort();
    }

    public List<WorkTimeItem> getAllWorkTime(){return workTime.getAllWorkTime();}

    public WorkTimeItem findWorkTimeByName(String name){
        List<WorkTimeItem> list=workTime.getAllWorkTime();
        WorkTimeItem workTimeItem=null;
        for (WorkTimeItem item:list){
            if (item.getName().equals(name)){
                workTimeItem=item;
                break;
            }
        }
        return workTimeItem;
    }
}
