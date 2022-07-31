package com.ityj.springcloud;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoDataSourceProxy
@MapperScan(basePackages = {"com.ityj.springcloud.mapper"})
public class SeataAccount2003Starter {

    public static void main(String[] args) {
        SpringApplication.run(SeataAccount2003Starter.class, args);
    }
}
