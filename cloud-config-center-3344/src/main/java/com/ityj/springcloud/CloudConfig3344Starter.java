package com.ityj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer   // 作为config配置的服务端
public class CloudConfig3344Starter {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfig3344Starter.class, args);
    }
}
