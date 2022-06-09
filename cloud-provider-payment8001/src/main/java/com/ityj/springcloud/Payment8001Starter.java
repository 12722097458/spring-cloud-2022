package com.ityj.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ityj.springcloud.mapper")
public class Payment8001Starter {
    public static void main(String[] args) {
        SpringApplication.run(Payment8001Starter.class, args);
    }
}
