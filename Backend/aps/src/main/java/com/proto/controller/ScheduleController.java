package com.proto.controller;

import com.proto.pojo.*;
import com.proto.service.CraftService;
import com.proto.service.EquipmentService;
import com.proto.service.OrderService;
import com.proto.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    TeamService teamService;
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    OrderService orderService;
    @Autowired
    CraftService craftService;

    @ResponseBody
    @GetMapping("/{beginDate}/{endDate}")
    public Result scheduling(@PathVariable String beginDate,@PathVariable String endDate )throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date begin_date = simpleDateFormat.parse(beginDate);
        Date end_date = simpleDateFormat.parse(endDate);
        List<Order> orderList = orderService.findAll();
        ArrayList<Order> orderArrayList = new ArrayList<Order>();
        for(int i=0;i<orderList.size();i++){
            if(orderList.get(i).getDelivery_date().before(end_date)){
                orderArrayList.add(orderList.get(i));
            }
        }
        Schedule schedule = new Schedule(begin_date,end_date);
        ArrayList<ScheduleItem> scheduleItemArrayList = schedule.scheduling(orderArrayList,craftService.findAll(),teamService.findAll(),equipmentService.findAll());
        if(scheduleItemArrayList!=null)
            return new Result(true,"排程成功",scheduleItemArrayList);
        return new Result(false,"排程失败",scheduleItemArrayList);
    }
}
