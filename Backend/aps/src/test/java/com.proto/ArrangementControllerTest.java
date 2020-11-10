package com.proto;

import com.proto.controller.ArrangementController;
import com.proto.pojo.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArrangementControllerTest {
    @Autowired
    private ArrangementController arrangementController;

    @Test
    public void solve() throws Exception{
        Result result = arrangementController.solve("2020-10-10","2020-10-22");
        Assert.assertEquals(result.getMessage(),"排程成功");
    }
}
