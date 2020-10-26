package com.proto.service;

import com.proto.pojo.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {

    public boolean save(Order order);

    public boolean deleteById(Integer orderid);

    public boolean update(Order order);

    public Order findById(Integer orderid);

    public List<Order> findAll();
}
