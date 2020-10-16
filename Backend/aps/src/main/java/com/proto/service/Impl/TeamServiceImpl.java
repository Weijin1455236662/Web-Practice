package com.proto.service.Impl;

import com.proto.dao.TeamDao;
import com.proto.pojo.Team;
import com.proto.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamDao teamDao;

    public boolean save(Team team){
        try {
            teamDao.save(team);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean deleteById(Integer teamid){
        try {
            teamDao.deleteById(teamid);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean update(Team team){
        try {
            teamDao.save(team);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Team findById(Integer oid){
        Team team;
        try {
            team = teamDao.findById(oid).get();
        }catch (Exception e){
            return null;
        }
        return team;
    }
}
