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
        List<Timeslot> timeslotList = new ArrayList<>();
        List<Equipment> equipmentList = equipmentService.findAll();

        List<Team> teamList = teamService.findAll();
        int tag[] = new int[10];
        buildTeamLists(teamList,teamLists,tag,0);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date begin_date = simpleDateFormat.parse(beginDate);
        Date end_date = simpleDateFormat.parse(endDate);
        List<Order> orderList = orderService.findAll();
        List<Craft> craftList = craftService.findAll();
        Order currentOrder=null;
        Craft currentCraft=null;
        int subOrderNum = 0;
        int subOrderId = 0;
        for(int i=0;i<orderList.size();i++){
            for(int j=0;j<craftList.size();j++){
                if(orderList.get(i).getMaterial_code().equals(craftList.get(j).getMaterial_code())){
                    currentOrder = orderList.get(i);
                    currentCraft = craftList.get(j);
                    subOrderNum = currentOrder.getQuantity()/currentCraft.getCapacity();
                    if(currentOrder.getQuantity()%currentCraft.getCapacity()!=0){
                        subOrderNum += 1;
                    }
                    for(int k=0;k<subOrderNum;k++){
                        subOrderId += 1;
                        subOrderList.add(new SubOrder((long)subOrderId,(long)currentOrder.getOrderid(),currentOrder.getMaterial_code(),currentCraft.getCapacity(),currentCraft.getHuman_num(),currentCraft.getHuman_res(),currentCraft.getEquipment_res()));
                    }
                }
            }
        }

        LocalDate begin__date = begin_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end__date = end_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate current__date = begin__date;
        while(current__date.isBefore(end__date)){
            for(int i=0;i<24;i++){
                timeslotList.add(new Timeslot(i,current__date));
            }
            current__date = current__date.plusDays(1);
        }

        Arrangement problem = new Arrangement(teamLists,equipmentList,timeslotList,subOrderList);
        UUID problemId =UUID.randomUUID();
        SolverJob<Arrangement,UUID> solverJob = solverManager.solve(problemId,problem);
        Arrangement solution = null;
        solution = solverJob.getFinalBestSolution();
        if(solution!=null)
            return new Result(true,"排程成功",solution);
        return new Result(false,"排程失败",solution);
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
