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

    public boolean deleteByOrderNum(Integer orderNum){
        try {
            OrderDao.deleteById(orderNum);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean update(Order order){
        return save(order);
    }

    public Order findByOrderNum(Integer orderNum){
        Order order;
        try {
            order = orderDao.findById(orderNum).get();
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
