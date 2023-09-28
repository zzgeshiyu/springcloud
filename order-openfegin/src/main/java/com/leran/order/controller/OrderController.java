package com.leran.order.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import com.leran.order.fegin.ProductFeignService;
import com.leran.order.fegin.StockFeignService;
import com.springstarter.leran.starter.service.MyJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private StockFeignService stockFeignService;

    @Autowired
    private ProductFeignService productFeignService;
    @Autowired
    private MyJsonService myJsonService;

    @RequestMapping("add")
    public String add() {
        System.out.println("下单成功");
        String result = stockFeignService.reduct();
        System.out.println("result:"+ result);
        return "下单成功， stock-service result:" + result;
    }

    @GetMapping("/search")
    public String search() {
        stockFeignService.reduct();
        return myJsonService.toMyJson(
                productFeignService.getProduct("test")
        );
    }
}
