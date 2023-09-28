package com.leran.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorkApplication.class, args);
    }

}
