package com.ityj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class StreamConsumer8803Starter {
    public static void main(String[] args) {
        SpringApplication.run(StreamConsumer8803Starter.class, args);
    }
}
