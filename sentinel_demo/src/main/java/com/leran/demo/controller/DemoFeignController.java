package com.leran.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.leran.demo.feign.ProductFeignService;
import com.leran.demo.feign.StockFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demoFeign")
@Slf4j
public class DemoFeignController {

    @Autowired(required = false)
    private StockFeignService stockFeignService;

    @Autowired(required = false)
    private ProductFeignService productFeignService;

    @GetMapping("/product")
    @SentinelResource("demoFeign-product")
    public String getProduct() {
        return JSONObject.toJSONString(productFeignService.getProduct("test"));
    }

    @GetMapping("/deduct")
    @SentinelResource("demoFeign-deduct")
    public String deduct() {
        return JSONObject.toJSONString(stockFeignService.deduct());
    }

}
