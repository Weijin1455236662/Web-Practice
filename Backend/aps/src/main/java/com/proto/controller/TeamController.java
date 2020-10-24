package com.proto.controller;

import com.proto.pojo.Result;
import com.proto.pojo.Team;
import com.proto.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;

    @ResponseBody
    @GetMapping("/{teamid}")
    public Result findById(@PathVariable Integer teamid){
        Team team = teamService.findById(teamid);
        if(team!=null)
            return new Result(true,"查询成功",team);
        return new Result(false,"查询失败",team);
    }

    @ResponseBody
    @PostMapping
    public Result save(@RequestBody Team team){
        boolean flag = teamService.save(team);
        if(flag!=false)
            return new Result(true,"添加成功");
        return new Result(false,"添加失败");
    }

    @ResponseBody
    @PutMapping("/{teamid}")
    public Result update(@PathVariable Integer teamid,@RequestBody Team team) {
        team.setTeamid(teamid);
        boolean flag = teamService.update(team);
        if (flag != false)
            return new Result(true, "修改成功");
        return new Result(false, "修改失败");
    }

    @ResponseBody
    @DeleteMapping("/{teamid}")
    public Result delete(@PathVariable Integer teamid){
        boolean flag = teamService.deleteById(teamid);
        if (flag != false)
            return new Result(true, "删除成功");
        return new Result(false, "删除失败");
    }
}
