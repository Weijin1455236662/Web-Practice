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

    @PlanningVariable(valueRangeProviderRefs = "teamListRange")
    private TeamList teamList;
    @PlanningVariable(valueRangeProviderRefs = "equipmentRange")
    private Equipment equipment;
    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    private Timeslot timeslot;

    public SubOrder() {
    }

    public SubOrder(Long id, Long parent_id, int material_code, int quantity, int capacity) {
        this.id = id;
        this.parent_id = parent_id;
        this.material_code = material_code;
        this.quantity = quantity;
        this.capacity = capacity;
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
}
