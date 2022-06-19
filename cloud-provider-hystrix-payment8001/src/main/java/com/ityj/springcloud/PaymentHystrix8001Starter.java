package com.ityj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentHystrix8001Starter {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrix8001Starter.class, args);
    }
}
