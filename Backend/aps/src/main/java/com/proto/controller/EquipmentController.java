package com.proto.controller;

import com.proto.pojo.Equipment;
import com.proto.pojo.Result;
import com.proto.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;

    @ResponseBody
    @GetMapping
    public Result findAll(){
        List<Equipment> list = equipmentService.findAll();
        if(list!=null)
            return new Result(true,"查询成功",list);
        return new Result(false,"查询失败",list);
    }

    @ResponseBody
    @PostMapping
    public Result save(@RequestBody Equipment equipment){
        boolean flag = equipmentService.save(equipment);
        if(flag!=false)
            return new Result(true,"添加成功");
        return new Result(false,"添加失败");
    }

    @ResponseBody
    @PutMapping("/{equipmentId}")
    public Result update(@PathVariable Integer EquipmentId,@RequestBody Equipment equipment) {
        equipment.setId(EquipmentId);
        boolean flag = equipmentService.update(equipment);
        if (flag != false)
            return new Result(true, "修改成功");
        return new Result(false, "修改失败");
    }

    @ResponseBody
    @DeleteMapping("/{equipmentId}")
    public Result delete(@PathVariable Integer equipmetId){
        boolean flag = equipmentService.deleteById(equipmetId);
        if (flag != false)
            return new Result(true, "删除成功");
        return new Result(false, "删除失败");
    }

}
