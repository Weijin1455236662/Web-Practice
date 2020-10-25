package com.proto.service;

import com.proto.pojo.Order;

import java.util.List;

public interface OrderService {

    public boolean save(Order order);

    public boolean deleteByOrderNum(Integer orderNum);

    public boolean update(Order order);

    public Order findByOrderNum(Integer orderNum);

    public List<Order> findAll();
}
