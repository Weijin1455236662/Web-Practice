package com.proto.service;

import com.proto.pojo.Team;

import java.util.List;

public interface TeamService {

    public boolean save(Team team);

    public boolean deleteById(Integer teamid);

    public boolean update(Team team);

    public Team findById(Integer oid);

    public List<Team> findAll();
}
