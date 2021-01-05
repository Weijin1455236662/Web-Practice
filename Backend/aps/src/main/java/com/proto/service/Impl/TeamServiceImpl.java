package com.proto.service.Impl;

import com.proto.dao.TeamDao;
import com.proto.pojo.Team;
import com.proto.service.TeamService;
import com.proto.service.client.attendanceservice.schedule.ScheduleItem;
import com.proto.service.client.attendanceservice.schedule.ScheduleServiceForApp;
import com.proto.service.client.attendanceservice.worktime.WorkTimeItem;
import com.proto.service.client.attendanceservice.worktime.WorkTimeServiceForApp;
import com.proto.service.client.erpservice.resource.ResourceItem;
import com.proto.service.client.erpservice.resource.ResourceServiceForApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamDao teamDao;

    public boolean save(Team team){
        try {
            teamDao.save(team);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean deleteById(Integer teamid){
        try {
            teamDao.deleteById(teamid);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean update(Team team){
        try {
            teamDao.save(team);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Team findById(Integer oid){
        Team team;
        try {
            team = teamDao.findById(oid).get();
        }catch (Exception e){
            return null;
        }
        return team;
    }

    public List<Team> findAll(){
        List<Team> teamList;
        try{
            teamList = teamDao.findAll();
        }catch (Exception e){
            return null;
        }
        return teamList;
    }

    public boolean importTeamData(){
        try {
//            ResourceServiceForApp resourceServiceForApp=new ResourceServiceForApp();
//            ScheduleServiceForApp scheduleServiceForApp=new ScheduleServiceForApp();
//            WorkTimeServiceForApp workTimeServiceForApp=new WorkTimeServiceForApp();
//            List<ResourceItem> humanResourceList=resourceServiceForApp.getHumanResource();
//            System.out.println(humanResourceList.size());
//            for (ResourceItem item:humanResourceList){
//                Team t=new Team();
//                t.setName(item.getName());
//                t.setNum(item.getAmount());
//                t.setBegin_day(1);
//                t.setEnd_day(5);
//                ScheduleItem scheduleItem=scheduleServiceForApp.findScheduleByName(item.getName());
//                if (scheduleItem!=null) {
//                    String workTime = scheduleItem.getWorkTime();
//                    String[] timeGap = workTimeServiceForApp.findWorkTimeByName(workTime).getTime().split("-");
//                    t.setBegin_time(Integer.parseInt(timeGap[0].substring(0, 2)));
//                    t.setEnd_time(Integer.parseInt(timeGap[1].substring(0, 2)));
//                } else {
//                    t.setBegin_time(0);
//                    t.setEnd_time(0);
//                }
//                teamDao.save(t);
//
//            }
            teamDao.deleteAll();
            ScheduleServiceForApp scheduleServiceForApp = new ScheduleServiceForApp();
            List<ScheduleItem> scheduleItemList = scheduleServiceForApp.getAllSchedule();
            for(ScheduleItem scheduleItem: scheduleItemList){
                if(!scheduleItem.getWorkTime().equals("全天")){
                    Team t=new Team();
                    String name = scheduleItem.getName();
                    name = name.replaceAll("（", " (").replaceAll("）",")").replaceAll(" ", "");
                    t.setName(name.split("\\(")[0]);
                    t.setNum(Integer.parseInt(name.split("\\(")[1].split("\\)")[0]));
                    t.setBegin_day(1);
                    t.setEnd_day(5);
                    if(scheduleItem.getWorkTime().equals("早班")){
                        t.setBegin_time(420);
                        t.setEnd_time(1080);
                    }else{
                        t.setBegin_time(1081);
                        t.setEnd_time(420);
                    }
                    teamDao.save(t);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
