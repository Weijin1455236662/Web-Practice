package com.proto.pojo;

import java.util.ArrayList;
import java.util.List;

public class TeamList {
    private List<Team> teamList;


    public TeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public TeamList() {
        teamList = new ArrayList<>();
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }
}
