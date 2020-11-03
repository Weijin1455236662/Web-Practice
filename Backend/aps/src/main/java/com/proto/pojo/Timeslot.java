package com.proto.pojo;

import java.time.LocalDate;

public class Timeslot {
    private int time;
    private LocalDate date;

    public Timeslot(int time, LocalDate date) {
        this.time = time;
        this.date = date;
    }

    public Timeslot() {
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
