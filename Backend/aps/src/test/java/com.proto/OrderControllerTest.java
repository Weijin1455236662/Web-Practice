package com.proto;

import com.proto.controller.OrderController;
import com.proto.pojo.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {

    @Autowired
    private OrderController orderController;

    @Test
    public void delete() throws Exception{
        Result result = orderController.delete(1);
        Assert.assertEquals(result.getMessage(),"删除成功");
    }
}
