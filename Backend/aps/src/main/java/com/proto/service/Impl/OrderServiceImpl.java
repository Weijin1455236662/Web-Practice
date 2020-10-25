package com.proto.service.Impl;

import com.proto.dao.OrderDao;
import com.proto.pojo.Order;
import com.proto.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    public boolean save(Order order){
        try {
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

}
