package com.proto.dao;

import com.proto.pojo.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDao extends JpaRepository<Team,Integer> {
}
