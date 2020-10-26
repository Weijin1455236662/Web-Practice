package com.proto.pojo;

import java.util.*;

public class Schedule {
    private Date begin_date;
    private Date end_date;

    public Schedule(Date begin_date, Date end_date) {
        this.begin_date = begin_date;
        this.end_date = end_date;
    }

    public ArrayList<ScheduleItem> scheduling(ArrayList<Order> orderArrayList, List<Craft> craftList,List<Team> teamList,List<Equipment> equipmentList){
        ArrayList<ScheduleItem> scheduleItemArrayList = new ArrayList<ScheduleItem>();
        Calendar c = Calendar.getInstance();
        c.setTime(begin_date);
        Integer currentDay;
        Order currentOrder=null;
        Craft currentCraft=null;
        ArrayList<Team> currentTeamList=null;
        ScheduleItem scheduleItem;
        while (c.getTime().before(end_date)){
            scheduleItem = new ScheduleItem(c.getTime());
            currentDay = c.get(Calendar.DAY_OF_WEEK)-1;
            if(currentDay==0){
                currentDay = 7;
            }
            for(int i=0;i<24;i++){
                currentTeamList = new ArrayList<Team>();
                for(int j=0;j<teamList.size();j++){
                    if(teamList.get(j).getBegin_day()<=currentDay&&teamList.get(j).getEnd_day()>=currentDay){
                        if(teamList.get(j).getBegin_time()<teamList.get(j).getEnd_time()){
                            if(teamList.get(j).getBegin_time()<=i&&teamList.get(j).getEnd_time()>=i){
                                currentTeamList.add(teamList.get(j));
                            }
                        }else{
                            if(teamList.get(j).getBegin_time()<=i||teamList.get(j).getEnd_time()>=i){
                                currentTeamList.add(teamList.get(j));
                            }
                        }
                    }
                }
                currentOrder = null;
                for(int j=0;j<orderArrayList.size();j++){
                    if(orderArrayList.get(j).getQuantity()>0){
                        currentOrder = orderArrayList.get(j);
                        break;
                    }
                }

                if(currentTeamList.size()==0||currentOrder==null){
                    continue;
                }

                currentCraft=null;
                for(int j=0;j<craftList.size();j++) {
                    if (craftList.get(j).getMaterial_code() == currentOrder.getMaterial_code()) {
                        currentCraft = craftList.get(j);
                        break;
                    }
                }

                int k = 0;
                int previous = k;
                int num = 0;
                ArrayList<Integer> team_list=null;
                HashMap<Integer,ArrayList<Integer>> map=null;
                for(int j=0;j<equipmentList.size();j++){
                    while (num<currentCraft.getHuman_num()&&k<currentTeamList.size()){
                        num+=currentTeamList.get(k).getNum();
                        k++;
                    }
                    if(num>=currentCraft.getHuman_num()){
                        team_list = new ArrayList<Integer>();
                        team_list.add(currentCraft.getMaterial_code());
                        map = new HashMap<Integer,ArrayList<Integer>>();
                        for(int l=previous;l<k;l++) {
                            team_list.add(currentTeamList.get(l).getTeamid());
                        }
                        map.put(equipmentList.get(j).getEquipmentid(),team_list);
                        scheduleItem.addTeamScheduleByTime(map,i);
                        currentOrder.setQuantity(currentOrder.getQuantity()-currentCraft.getCapacity());
                        previous = k;
                        num = 0;
                    }else{
                        num = 0;
                        break;
                    }
                }
            }
            scheduleItemArrayList.add(scheduleItem);
            c.add(Calendar.DATE,1);
        }
        return scheduleItemArrayList;
    }
}
