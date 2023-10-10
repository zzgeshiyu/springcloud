package com.leran.stock.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StorkController {

    @Value("${server.port}")
    String port;

    @SentinelResource(value = "stock-deduct")
    @GetMapping("/deduct")
    public String deduct() {
        System.out.println("库存扣减 "+port);
        return "扣减1 "+port;
    }
}
