package OrderService.Order;


import java.util.Date;

public class OrderItem {
    private String orderNumber;
    private String materialCode;
    private int quantity;
    private Date  deliveryDate;
    private String remark;
    private int length;

    public OrderItem(String orderNumber, String materialCode, int quantity, Date deliveryDate) {
        this.orderNumber = orderNumber;
        this.materialCode = materialCode;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        remark="";
        length=0;
    }

    public OrderItem(String orderNumber, String materialCode, int quantity, Date deliveryDate, String remark, int length) {
        this.orderNumber = orderNumber;
        this.materialCode = materialCode;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        this.remark = remark;
        this.length = length;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
