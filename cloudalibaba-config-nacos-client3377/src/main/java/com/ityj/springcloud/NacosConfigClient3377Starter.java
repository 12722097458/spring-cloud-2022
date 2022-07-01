package com.ityj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigClient3377Starter {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClient3377Starter.class, args);
    }

}
