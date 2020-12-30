package com.proto.pojo;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.time.LocalDate;

@PlanningEntity
public class SubOrder implements Comparable<SubOrder> {
    private long id;
    private long parent_id;
    private long product_id;
    private Integer material_code;
    private int type; //0:打弹片 1:装配 2:测试/印字
    private int capacity;
    private String human_res;
    private String equipment_res;
    private int last_time;
    private int urgent;
    private LocalDate delivery_date;

    @PlanningVariable(valueRangeProviderRefs = "teamListRange")
    private TeamList teamList;
    @PlanningVariable(valueRangeProviderRefs = "equipmentRange")
    private Equipment equipment;
    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    private Timeslot timeslot;

    public SubOrder() {
    }

    public SubOrder(long id, long parent_id, long product_id, Integer material_code, int type, int capacity, String human_res, String equipment_res, int last_time, int urgent, LocalDate delivery_date) {
        this.id = id;
        this.parent_id = parent_id;
        this.product_id = product_id;
        this.material_code = material_code;
        this.type = type;
        this.capacity = capacity;
        this.human_res = human_res;
        this.equipment_res = equipment_res;
        this.last_time = last_time;
        this.urgent = urgent;
        this.delivery_date = delivery_date;
    }

    @Override
    public int compareTo(SubOrder o) {
        return o.getUrgent()-this.urgent;
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

    public int getLast_time() {
        return last_time;
    }

    public void setLast_time(int last_time) {
        this.last_time = last_time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getMaterial_code() {
        return material_code;
    }

    public void setMaterial_code(Integer material_code) {
        this.material_code = material_code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public int getUrgent() {
        return urgent;
    }

    public void setUrgent(int urgent) {
        this.urgent = urgent;
    }

    public LocalDate getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(LocalDate delivery_date) {
        this.delivery_date = delivery_date;
    }
}
