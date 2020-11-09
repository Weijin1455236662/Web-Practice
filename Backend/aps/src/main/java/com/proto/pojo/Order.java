package com.proto.pojo;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderid;
    @NotNull(message = "代码不能为空")
    private Integer material_code;
    @Min(value = 1, message = "数量不能小于1")
    private Integer quantity;
    @NotNull(message = "日期不能为空")
    private Date delivery_date;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getMaterial_code() {
        return material_code;
    }

    public void setMaterial_code(Integer material_code) {
        this.material_code = material_code;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }
}
