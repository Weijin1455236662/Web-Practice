package com.proto;

import com.proto.controller.EquipmentController;
import com.proto.pojo.Equipment;
import com.proto.pojo.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EquipmentControllerTest {

    @Autowired
    private EquipmentController equipmentController;

    @Test
    public void save() throws Exception{
        Equipment equipment = new Equipment();
        equipment.setEquipmentid(100);
        equipment.setName("测试设备");
        equipment.setType("测试类型");
        equipment.setAmount(10);
        Result result = equipmentController.save(equipment);
        Assert.assertEquals(result.getMessage(),"添加成功");
    }
}
