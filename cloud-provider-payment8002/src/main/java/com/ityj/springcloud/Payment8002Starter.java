package com.ityj.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.ityj.springcloud.mapper")
@EnableEurekaClient
public class Payment8002Starter {
    public static void main(String[] args) {
        SpringApplication.run(Payment8002Starter.class, args);
    }
}
