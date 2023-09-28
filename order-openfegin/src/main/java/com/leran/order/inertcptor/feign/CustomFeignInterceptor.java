package com.leran.order.inertcptor.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * openFeign 拦截器
 */
public class CustomFeignInterceptor implements RequestInterceptor {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // todo
        requestTemplate.header("xxxxx","xxxxxx");
//        requestTemplate.query("id", "11111");
//        requestTemplate.uri("/9");
        logger.info("open feign 拦截器");
    }
}
