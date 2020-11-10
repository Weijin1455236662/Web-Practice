package com.proto;

import com.proto.controller.TeamController;
import com.proto.pojo.Team;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamControllerTest {

    @Autowired
    private TeamController teamController;

    @Test
    public void update() throws Exception{
        Team team = new Team();
        team.setNum(-10);
        team.setName("测试小组");
        team.setBegin_day(1);
        team.setEnd_day(2);
        team.setBegin_time(2);
        team.setEnd_time(10);
        try {
            teamController.update(1,team);
        }catch (Exception e){
            Assert.assertEquals(e.getMessage(),"update.team.num: 人数不能小于1");
        }
    }
}
