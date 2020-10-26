package com.proto.dao;

import com.proto.pojo.Craft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CraftDao extends JpaRepository<Craft,Integer> {
}
