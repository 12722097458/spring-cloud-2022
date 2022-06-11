package com.ityj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerOrderZK80Starter {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderZK80Starter.class, args);
    }
}
