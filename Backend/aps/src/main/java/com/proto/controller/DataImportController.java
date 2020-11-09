package com.proto.controller;

import com.proto.pojo.Result;
import com.proto.service.CraftService;
import com.proto.service.EquipmentService;
import com.proto.service.OrderService;
import com.proto.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@Validated
@RequestMapping("/data")
public class DataImportController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private CraftService craftService;
    @ResponseBody
    @GetMapping("/initialize")
    public Result importData(){
        if (orderService.importOrderData()&&teamService.importTeamData()&&equipmentService.importEquipmentData()&&craftService.importCraftData()){
            return new Result(true,"数据导入成功");
        }
        else return new Result(false,"数据导入失败");
    }
}
