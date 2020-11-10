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
    @Min(value = 1, message = "日期不能小于1")
    @Max(value = 7, message = "日期不能大于7")
    private Integer begin_day;
    @Min(value = 1, message = "日期不能小于1")
    @Max(value = 7, message = "日期不能大于7")
    private Integer end_day;
    @Min(value = 0, message = "时间不能小于0")
    @Max(value = 23, message = "日期不能大于23")
    private Integer begin_time;
    @Min(value = 0, message = "时间不能小于0")
    @Max(value = 23, message = "日期不能大于23")
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
