package com.proto;

import com.proto.controller.ArrangementController;
import com.proto.controller.EquipmentController;
import com.proto.controller.OrderController;
import com.proto.controller.TeamController;
import com.proto.pojo.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest {

    @Autowired
    ArrangementController arrangementController;
    @Autowired
    EquipmentController equipmentController;
    @Autowired
    OrderController orderController;
    @Autowired
    TeamController teamController;

    @Test
    public void routineOperation()throws Exception{
        Result result;
        result = teamController.delete(2);
        Assert.assertEquals(result.getMessage(),"删除成功");
        Order order = new Order();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        order.setMaterial_code(1);
        order.setDelivery_date(simpleDateFormat.parse("2020-10-27"));
        order.setQuantity(1000);
        result = orderController.save(order);
        Assert.assertEquals(result.getMessage(),"添加成功");
        result = equipmentController.delete(1);
        Assert.assertEquals(result.getMessage(),"删除成功");
        result = arrangementController.solve("2020-10-10","2020-10-20");
        Assert.assertEquals(result.getMessage(),"排程成功");
    }
}
