package com.leran.order.config;

import com.leran.order.inertcptor.feign.CustomFeignInterceptor;
import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局配置 @Configuration 会将配置作用所有的服务提供方
 * 如果需要单独配置日志，需要注释 这个注解后  在Feign接口下 @FeignClient
 * 对应中配置
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 全局配置请求超时时间
     * @return
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(50000,10000);

    }

    @Bean
    public CustomFeignInterceptor customFeignInterceptor() {
        return new CustomFeignInterceptor();
    }
}
