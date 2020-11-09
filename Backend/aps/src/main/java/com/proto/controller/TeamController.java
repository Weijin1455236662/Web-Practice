package com.proto.controller;

import com.proto.pojo.Result;
import com.proto.pojo.Team;
import com.proto.service.CraftService;
import com.proto.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@Validated
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;
    @Autowired
    CraftService craftService;

    @ResponseBody
    @GetMapping("/{teamid}")
    public Result findById(@PathVariable @Min(value = 1, message = "id不合法") Integer teamid){
        Team team = teamService.findById(teamid);
        if(team!=null)
            return new Result(true,"查询成功",team);
        return new Result(false,"查询失败",team);
    }

    @ResponseBody
    @GetMapping("/all")
    public Result findAll(){
        List<Team> teamList = teamService.findAll();
        if(teamList!=null)
            return new Result(true,"查询成功",teamList);
        return new Result(false,"查询失败",teamList);
    }

    @ResponseBody
    @PostMapping
    public Result save(@RequestBody @Valid Team team){
        boolean flag = teamService.save(team);
        if(flag!=false)
            return new Result(true,"添加成功");
        return new Result(false,"添加失败");
    }

    @ResponseBody
    @PutMapping("/{teamid}")
    public Result update(@PathVariable @Min(value = 1, message = "id不合法") Integer teamid,@RequestBody @Valid Team team) {
        team.setTeamid(teamid);
        boolean flag = teamService.update(team);
        if (flag != false)
            return new Result(true, "修改成功");
        return new Result(false, "修改失败");
    }

    @ResponseBody
    @DeleteMapping("/{teamid}")
    public Result delete(@PathVariable @Min(value = 1, message = "id不合法") Integer teamid){
        boolean flag = teamService.deleteById(teamid);
        if (flag != false)
            return new Result(true, "删除成功");
        return new Result(false, "删除失败");
    }

    @ResponseBody
    @GetMapping("/import")
    public Result importData(){
        if (teamService.importTeamData()){
            return new Result(true,"数据导入成功");
        }
        else return new Result(false,"数据导入失败");
    }

    @ResponseBody
    @GetMapping("/importCraft")
    public Result importCraftData(){
        if (craftService.importCraftData()){
            return new Result(true,"数据导入成功");
        }
        else return new Result(false,"数据导入失败");
    }
}
