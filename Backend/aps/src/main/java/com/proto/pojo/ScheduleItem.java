package com.proto.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ScheduleItem {
    private ArrayList<HashMap<Integer,ArrayList<Integer>>> item[] = new ArrayList[24];
    private Date date;

    public ScheduleItem(Date date) {
        this.date = date;
        for(int i=0;i<24;i++){
            item[i] = new ArrayList<HashMap<Integer,ArrayList<Integer>>>();
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean addTeamScheduleByTime(HashMap<Integer,ArrayList<Integer>> teamSchedule, int time){
        return item[time].add(teamSchedule);
    }

    public ArrayList<HashMap<Integer,ArrayList<Integer>>> getTeamScheduleByTime(int time){
        return item[time];
    }

    public ArrayList<HashMap<Integer, ArrayList<Integer>>>[] getItem() {
        return item;
    }
}
