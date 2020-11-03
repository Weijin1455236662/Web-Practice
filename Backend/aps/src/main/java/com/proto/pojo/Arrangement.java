package com.proto.pojo;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@PlanningSolution
public class Arrangement {

    @ValueRangeProvider(id = "teamListRange")
    @ProblemFactCollectionProperty
    private List<TeamList> teamLists;

    @ValueRangeProvider(id = "equipmentRange")
    @ProblemFactCollectionProperty
    private List<Equipment> equipmentList;

    @ValueRangeProvider(id = "timeslotRange")
    @ProblemFactCollectionProperty
    private List<Timeslot> timeslotList;

    @PlanningEntityCollectionProperty
    private List<SubOrder> subOrderList;

    @PlanningScore
    private HardSoftScore score;

    public Arrangement() {
    }

    public Arrangement(List<TeamList> teamLists, List<Equipment> equipmentList, List<Timeslot> timeslotList, List<SubOrder> subOrderList) {
        this.teamLists = teamLists;
        this.equipmentList = equipmentList;
        this.timeslotList = timeslotList;
        this.subOrderList = subOrderList;
    }

    public List<TeamList> getTeamLists() {
        return teamLists;
    }

    public void setTeamLists(List<TeamList> teamLists) {
        this.teamLists = teamLists;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<Timeslot> getTimeslotList() {
        return timeslotList;
    }

    public void setTimeslotList(List<Timeslot> timeslotList) {
        this.timeslotList = timeslotList;
    }

    public List<SubOrder> getSubOrderList() {
        return subOrderList;
    }

    public void setSubOrderList(List<SubOrder> subOrderList) {
        this.subOrderList = subOrderList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }
}
