package com.leran.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StorkController {

    @RequestMapping("/reduct")
    public String reduct() {
        System.out.println("库存扣减");
        return "扣减1";
    }
}
