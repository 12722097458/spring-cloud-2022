package com.ityj.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.ityj.springcloud.mapper")
@EnableDiscoveryClient
public class Payment8004Starter {
    public static void main(String[] args) {
        SpringApplication.run(Payment8004Starter.class, args);
    }
}
