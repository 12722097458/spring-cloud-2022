package com.ityj.springcloud;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableAutoDataSourceProxy
@MapperScan(basePackages = {"com.ityj.springcloud.mapper"})
public class SeataOrder2001Starter {

    public static void main(String[] args) {
        SpringApplication.run(SeataOrder2001Starter.class, args);
    }
}
