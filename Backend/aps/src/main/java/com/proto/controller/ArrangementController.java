package com.proto.controller;

import com.proto.pojo.*;
import com.proto.service.CraftService;
import com.proto.service.EquipmentService;
import com.proto.service.OrderService;
import com.proto.service.TeamService;
import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@Validated
@RequestMapping("/arrangement")
public class ArrangementController {

    @Autowired
    private SolverManager<Arrangement, UUID> solverManager;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CraftService craftService;
    @Autowired
    private TeamService teamService;

    @ResponseBody
    @GetMapping("/{beginDate}/{endDate}")
    public Result solve(@PathVariable @Pattern(regexp = "^((?:19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$",message = "日期格式不正确，正确格式为：2014-01-01") String beginDate, @PathVariable @Pattern(regexp = "^((?:19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$",message = "日期格式不正确，正确格式为：2014-01-01") String endDate )throws Exception{
        List<SubOrder> subOrderList = new ArrayList<>();
        List<TeamList> teamLists = new ArrayList<>();
        List<Equipment> equipmentList = equipmentService.findAll();
        int begin_work_time = 0;
        int end_work_time = 0;

        List<Team> teamList = teamService.findAll();
        begin_work_time = teamList.get(0).getBegin_time();
        end_work_time = teamList.get(0).getEnd_time();
        TeamList team_list = null;
        for(int i=0;i<teamList.size();i++){
            team_list = new TeamList();
            team_list.getTeamList().add(teamList.get(i));
            teamLists.add(team_list);
            for(int j=i+1;j<teamList.size();j++){
                team_list = new TeamList();
                team_list.getTeamList().add(teamList.get(i));
                team_list.getTeamList().add(teamList.get(j));
                teamLists.add(team_list);
                for(int k=j+1;k<teamList.size();k++){
                    team_list = new TeamList();
                    team_list.getTeamList().add(teamList.get(i));
                    team_list.getTeamList().add(teamList.get(j));
                    team_list.getTeamList().add(teamList.get(k));
                    teamLists.add(team_list);
                }
            }
        }
        //int tag[] = new int[10];
        //buildTeamLists(teamList,teamLists,tag,0);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date begin_date = simpleDateFormat.parse(beginDate);
        Date end_date = simpleDateFormat.parse(endDate);
        List<Order> orderList = orderService.findAll();
        List<Craft> craftList = craftService.findAll();
        Order currentOrder=null;
        Craft currentCraft=null;
        int total = 0;
        int subOrderId = 0;
        for(int i=0;i<orderList.size();i++){
            for(int j=0;j<craftList.size();j++){
                if(orderList.get(i).getMaterial_code().equals(craftList.get(j).getMaterial_code())){
                    currentOrder = orderList.get(i);
                    currentCraft = craftList.get(j);
                    total = currentOrder.getQuantity();
                    for(int k=0;k<total;k++){
                        if(currentCraft.getPunch_capacity()!=-1){
                            subOrderId += 1;
                            subOrderList.add(new SubOrder((long)subOrderId,(long)currentOrder.getOrderid(), (long)k,currentOrder.getMaterial_code(), 0,-1,currentCraft.getPunch_human_res(),currentCraft.getPunch_equipment_res(),3600/currentCraft.getPunch_capacity()));
                        }
                        subOrderId += 1;
                        subOrderList.add(new SubOrder((long)subOrderId,(long)currentOrder.getOrderid(), (long)k,currentOrder.getMaterial_code(), 1, currentCraft.getHuman_num(),currentCraft.getHuman_res(),currentCraft.getEquipment_res(), 3600/currentCraft.getCapacity()));
                        if(currentCraft.getTest_capacity()!=-1){
                            subOrderId += 1;
                            subOrderList.add(new SubOrder((long)subOrderId,(long)currentOrder.getOrderid(), (long)k,currentOrder.getMaterial_code(), 2,-1,"",currentCraft.getTest_equipment_res(),3600/currentCraft.getTest_capacity()));
                        }
                    }
                }
            }
        }

        LocalDate begin__date = begin_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end__date = end_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate current__date = begin__date;
        long sub_subOrder_num = subOrderList.size()/begin__date.until(end__date, ChronoUnit.DAYS);
        int count = 0;
        List<SubOrder> sub_subOrderList;
        List<Timeslot> timeslotList;
        List<Arrangement> problemList = new ArrayList<>();
        List<SolverJob<Arrangement,UUID>> solverJobList = new ArrayList<>();
        List<Arrangement> solutionList = new ArrayList<>();
        while(count<subOrderList.size()) {
            if(current__date.getDayOfWeek().getValue()<1||current__date.getDayOfWeek().getValue()>5){
                current__date = current__date.plusDays(1);
                continue;
            }
            timeslotList = new ArrayList<>();
            sub_subOrderList = new ArrayList<>();
            for (int i = begin_work_time; i < end_work_time; i++) {
                timeslotList.add(new Timeslot(i, current__date));
            }
            for (int i = count; i - count < sub_subOrder_num && i < subOrderList.size(); i++) {
                sub_subOrderList.add(subOrderList.get(i));
            }
            count += sub_subOrder_num;
            problemList.add(new Arrangement(teamLists, equipmentList, timeslotList, sub_subOrderList));
            current__date = current__date.plusDays(1);
        }


        for(int i=0;i<problemList.size();i++){
            solverJobList.add(solverManager.solve(UUID.randomUUID(),problemList.get(i)));
        }
        for(int i=0;i<solverJobList.size();i++){
            solutionList.add(solverJobList.get(i).getFinalBestSolution());
        }
        List<List<SubOrder>> result = new ArrayList<>();
        if(solutionList.size()!=0) {
            for (int i = 0; i < solutionList.size(); i++) {
                result.add(solutionList.get(i).getSubOrderList());
            }
            return new Result(true,"排程成功",result);
        }
        return new Result(false,"排程失败",result);
    }

    public void buildTeamLists(List<Team> teamList, List<TeamList> teamLists, int[] tag, int n){

        if(n==teamList.size()){
            TeamList team_list = new TeamList();
            for(int i=0;i<teamList.size();i++){
                if(tag[i]==1){
                    team_list.getTeamList().add(teamList.get(i));
                }
            }
            if(team_list.getTeamList().size()>0){
                teamLists.add(team_list);
            }
            return;
        }
        tag[n] = 0;
        buildTeamLists(teamList,teamLists,tag,n+1);
        tag[n] = 1;
        buildTeamLists(teamList,teamLists,tag,n+1);
    }
}
