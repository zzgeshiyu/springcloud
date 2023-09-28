package com.leran.order.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    public RestTemplate restTemplate;
    @NacosInjected
    private NamingService namingService;

    @RequestMapping("add")
    public String add() {
        System.out.println("下单成功");
        String result = restTemplate.getForObject("http://stock-service/stock/reduct",String.class);
        System.out.println("result:"+ result);
        return "下单成功， stock-service result:" + result;
    }
}
