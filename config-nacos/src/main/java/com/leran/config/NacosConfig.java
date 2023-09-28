package com.leran.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class NacosConfig {
    /**
     * 无法动态感知配置的 值
     */
    @Value("${user.name}")
    private String userName;



    @Value("${user.age}")
    private String userAge;
//
//    public NacosConfig(String userAge) {
//        this.userAge = userAge;
//    }


//    @RefreshScope
    public String getUserName() {
        return userName;
    }

    public String getUserAge() {
        return userAge;
    }
}
