package com.proto.service.Impl;

import com.proto.dao.OrderDao;
import com.proto.pojo.Order;
import com.proto.service.OrderService;
import com.proto.service.client.order.OrderItem;
import com.proto.service.client.order.OrderServiceForApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    public boolean save(Order order){
        try {
            order.setUrgent(1);
            orderDao.save(order);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean deleteById(Integer orderid){
        try {
            orderDao.deleteById(orderid);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean update(Order order){
        try {
            orderDao.save(order);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Order findById(Integer orderid){
        Order order;
        try {
            order = orderDao.findById(orderid).get();
        }catch (Exception e){
            return null;
        }
        return order;
    }

    public List<Order> findAll(){
        List<Order> list;
        try {
            list=orderDao.findAll();
        }catch (Exception e){
            return null;
        }
        return list;
    }

    public boolean importOrderData(){
        try {
            orderDao.deleteAll();
            OrderServiceForApp orderServiceForApp = new OrderServiceForApp();
            List<OrderItem> rawItemList = orderServiceForApp.getAllOrders();
            for (OrderItem item : rawItemList) {
                Order order = new Order();
                order.setOrderid(Integer.parseInt(item.getOrderNumber()));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
                order.setDelivery_date(simpleDateFormat.parse(item.getDeliveryDate().toString().split("T")[0]));
                order.setMaterial_code(Integer.parseInt(item.getMaterialCode()));
                order.setQuantity(item.getQuantity());
                System.out.println(order.getDelivery_date());
                orderDao.save(order);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
