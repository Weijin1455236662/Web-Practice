package com.proto.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_team")
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamid;
    private String name;
    private Integer num;
    private Integer begin_day;
    private Integer end_day;
    private Integer begin_time;
    private Integer end_time;

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getBegin_day() {
        return begin_day;
    }

    public void setBegin_day(Integer begin_day) {
        this.begin_day = begin_day;
    }

    public Integer getEnd_day() {
        return end_day;
    }

    public void setEnd_day(Integer end_day) {
        this.end_day = end_day;
    }

    public Integer getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Integer begin_time) {
        this.begin_time = begin_time;
    }

    public Integer getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Integer end_time) {
        this.end_time = end_time;
    }
}
