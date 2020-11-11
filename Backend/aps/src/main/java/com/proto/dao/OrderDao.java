package com.proto.dao;

import com.proto.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Integer> {
    @Override
    void deleteAll();
}
