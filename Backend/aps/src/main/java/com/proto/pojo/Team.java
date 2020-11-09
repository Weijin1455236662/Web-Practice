package com.proto.pojo;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "t_team")
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamid;
    @NotNull(message = "姓名不能为空")
    private String name;
    @Min(value = 1, message = "人数不能小于1")
    private Integer num;
    //@Size(min = 1, max = 7, message = "日期不合法")
    @Min(value = 0)
    @Max(value = 24)
    private Integer begin_day;
    //@Size(min = 1, max = 7, message = "日期不合法")
    @Min(value = 0)
    @Max(value = 24)
    private Integer end_day;
    //@Size(min = 0, max = 24, message = "时间不合法")
    @Min(value = 0)
    @Max(value = 24)
    private Integer begin_time;
    //@Size(min = 0, max = 24, message = "时间不合法")
    @Min(value = 0)
    @Max(value = 24)
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
