package com.proto.dao;

import com.proto.pojo.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentDao extends JpaRepository<Equipment,Integer>{
}
