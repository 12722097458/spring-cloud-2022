package com.ityj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SentinelService8401Starter {
    public static void main(String[] args) {
        SpringApplication.run(SentinelService8401Starter.class, args);
    }
}
