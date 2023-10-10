package com.leran.product.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SentinelConfig {

    /**
     * 注解支持的配置bean
     * @return
     */
    @Bean
    public SentinelResourceAspect getSentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
