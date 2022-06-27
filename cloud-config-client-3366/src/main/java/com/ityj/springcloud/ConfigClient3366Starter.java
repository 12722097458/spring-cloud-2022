package com.ityj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConfigClient3366Starter {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClient3366Starter.class, args);
    }
}
