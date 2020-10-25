package com.proto.controller;

import com.proto.pojo.Result;
import com.proto.pojo.Order;
import com.proto.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;


    @ResponseBody
    @PostMapping
    public Result save(@RequestBody Order order){
        boolean flag = orderService.save(order);
        if(flag!=false)
            return new Result(true,"添加成功");
        return new Result(false,"添加失败");
    }

    @ResponseBody
    @DeleteMapping("/{orderNum}")
    public Result delete(@PathVariable Integer orderNum){
        boolean flag = orderService.deleteByOrderNum(orderNum);
        if (flag != false)
            return new Result(true, "删除成功");
        return new Result(false, "删除失败");
    }

    @ResponseBody
    @PutMapping("/{orderNum}")
    public Result update(@PathVariable Integer orderNum,@RequestBody Order order) {
        order.setOrderNumber(orderNum);
        boolean flag = orderService.update(order);
        if (flag != false)
            return new Result(true, "修改成功");
        return new Result(false, "修改失败");
    }

    @ResponseBody
    @GetMapping("/{orderNum}")
    public Result findById(@PathVariable Integer orderNum){
        Order order = orderService.findByOrderNum(orderNum);
        if(order!=null)
            return new Result(true,"查询成功",order);
        return new Result(false,"查询失败",order);
    }

    @ResponseBody
    @GetMapping
    public Result findAll(){
        List list = orderService.findAll();
        if(list!=null)
            return new Result(true,"查询成功",list);
        return new Result(false,"查询失败",list);
    }
}
