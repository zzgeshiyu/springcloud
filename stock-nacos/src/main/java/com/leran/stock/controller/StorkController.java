package com.leran.stock.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.leran.stock.config.NacosConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StorkController {

    @Value("${server.port}")
    String port;
    @Autowired
    NacosConfig nacosConfig;

    @SentinelResource(value = "stock-deduct")
    @GetMapping("/deduct")
    public String deduct() {
        System.out.println("库存扣减 "+port);
        return "扣减1 "+port;
    }

    @SentinelResource(value = "stock-get")
    @GetMapping("/get")
    public String get() {
        System.out.println("库存扣减 "+port);
        return nacosConfig.getUserName()+"扣减1 "+port;
    }

}
