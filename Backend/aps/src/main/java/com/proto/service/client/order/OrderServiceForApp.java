package com.proto.service.client.order;

import java.util.List;

public class OrderServiceForApp {


    public List<OrderItem> getAllOrders(){
        OrderService os=new OrderService();
        Order o=os.getOrderPort();
        List<OrderItem> list=o.getAllOrders();
        return list;
    }
}
