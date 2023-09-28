package com.leran.product.controller;

import com.leran.product.model.ProductModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/getProduct/{code}")
    public List<ProductModel> getProduct(@PathVariable(name = "code") String code){
        List<ProductModel> productModels = new ArrayList<>();
        System.out.println(code+"=============");
        for (int i = 0; i < 10; i++) {
            ProductModel model = new ProductModel();
            model.setCode("003"+i);
            model.setName("产品编码1"+i);
            model.setStatus((i+1)%2 == 0);
            productModels.add(model);
        }
        return productModels;
    }
}
