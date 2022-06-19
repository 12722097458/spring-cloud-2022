package com.ityj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker  // 开启服务降级 fallback
public class PaymentHystrix8001Starter {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrix8001Starter.class, args);
    }
}
