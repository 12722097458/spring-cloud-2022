package com.ityj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients   // openfeign
public class ConsumerOpenFeignOrder80Starter {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOpenFeignOrder80Starter.class, args);
    }
}
