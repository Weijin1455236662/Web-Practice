package com.proto.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_craft")
public class Craft implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer craftid;
    private Integer material_code;
    private Integer human_num;
    private Integer capacity;
    private String human_res;
    private String equipment_res;

    public Integer getCraftid() {
        return craftid;
    }

    public void setCraftid(Integer craftid) {
        this.craftid = craftid;
    }

    public Integer getMaterial_code() {
        return material_code;
    }

    public void setMaterial_code(Integer material_code) {
        this.material_code = material_code;
    }

    public Integer getHuman_num() {
        return human_num;
    }

    public void setHuman_num(Integer human_num) {
        this.human_num = human_num;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
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
