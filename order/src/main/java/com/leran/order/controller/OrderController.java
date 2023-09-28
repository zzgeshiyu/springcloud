package com.leran.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    public RestTemplate restTemplate;

    @RequestMapping("add")
    public String add() {
        System.out.println("下单成功");
        String result = restTemplate.getForObject("http://localhost:8011/stock/reduct",String.class);
        System.out.println("result:"+ result);
        return "1";
    }
}
