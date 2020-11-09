package com.proto.pojo;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class SubOrder {
    private Long id;
    private Long parent_id;
    private int material_code;
    private int quantity;
    private int capacity;
    private String human_res;
    private String equipment_res;

    @PlanningVariable(valueRangeProviderRefs = "teamListRange")
    private TeamList teamList;
    @PlanningVariable(valueRangeProviderRefs = "equipmentRange")
    private Equipment equipment;
    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    private Timeslot timeslot;

    public SubOrder() {
    }

    public SubOrder(Long id, Long parent_id, int material_code, int quantity, int capacity, String human_res, String equipment_res) {
        this.id = id;
        this.parent_id = parent_id;
        this.material_code = material_code;
        this.quantity = quantity;
        this.capacity = capacity;
        this.human_res = human_res;
        this.equipment_res = equipment_res;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public int getMaterial_code() {
        return material_code;
    }

    public void setMaterial_code(int material_code) {
        this.material_code = material_code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TeamList getTeamList() {
        return teamList;
    }

    public void setTeamList(TeamList teamList) {
        this.teamList = teamList;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getHuman_res() {
        return human_res;
    }

    public void setHuman_res(String human_res) {
        this.human_res = human_res;
    }

    public String getEquipment_res() {
        return equipment_res;
    }

    public void setEquipment_res(String equipment_res) {
        this.equipment_res = equipment_res;
    }
}
