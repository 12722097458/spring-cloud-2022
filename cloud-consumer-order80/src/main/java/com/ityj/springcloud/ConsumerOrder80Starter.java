package com.ityj.springcloud;

import com.ityj.myrule.RibbonRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = {RibbonRuleConfig.class})
public class ConsumerOrder80Starter {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrder80Starter.class, args);
    }
}
