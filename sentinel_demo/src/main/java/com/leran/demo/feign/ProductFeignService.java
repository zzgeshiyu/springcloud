package com.leran.demo.feign;


import com.leran.demo.config.FeignConfig;
import com.leran.demo.outmodel.ProductModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 *  name 是rest接口服务名
 *  path 指定调用rest接口所在的StockController指定的 @RequestMapping
 */
@FeignClient(name = "product-service", path = "/product", configuration = FeignConfig.class)
public interface ProductFeignService {

    //声明需要调用的rest接口对应的方法
    @GetMapping("/getProduct/{code}")
    List<ProductModel> getProduct(@PathVariable String code);
}
