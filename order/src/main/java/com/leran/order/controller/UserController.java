package com.leran.order.controller;

import com.leran.order.model.UserDO;
import com.springstarter.leran.starter.service.MyJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public MyJsonService myJsonService;

    @RequestMapping("add")
    public String add() {
        UserDO userDO = new UserDO();
        userDO.setGender("男");
        userDO.setId("001");
        userDO.setName("刘从");
        myJsonService.toMyJson(userDO);
        return myJsonService.toMyJson(userDO);
    }
}
