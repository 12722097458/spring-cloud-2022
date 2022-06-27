package com.ityj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamPayment8801Starter {
    public static void main(String[] args) {
        SpringApplication.run(StreamPayment8801Starter.class, args);
    }
}
