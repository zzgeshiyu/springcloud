package com.leran.controller;

import com.alibaba.nacos.api.exception.NacosException;
import com.leran.config.NacosConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {
    /**
     * 无法动态感知配置的 值
     */
    @Autowired
    private NacosConfig nacosConfig;

//    @RefreshScope("${user.age}")
//    private String userAge;
//
//    public ConfigController(String userAge) {
//        this.userAge = userAge;
//    }


    @GetMapping("/get")
    public String getConfig() throws NacosException {

        return "nacos config, " +
                "name:"+ nacosConfig.getUserName() +
                " age:"+nacosConfig.getUserAge();
    }
}
