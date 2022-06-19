package com.ityj.springcloud.service.impl;

import com.ityj.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String success(Long id) {
        return Thread.currentThread().getName() + "-success-" + id;
    }

    @SneakyThrows
    @Override
    @HystrixCommand(fallbackMethod = "timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")  //3秒钟以内就是正常的业务逻辑
    })
    public String timeout(Long id) {
        //Double.valueOf(null);
        long time = 2;
        TimeUnit.SECONDS.sleep(time);
        return Thread.currentThread().getName() + "-timeout:" + time + "-" + id;
    }

    // 兜底策略
    public String timeoutHandler(Long id) {
        return Thread.currentThread().getName() + "-timeoutHandler: 程序繁忙，请稍后再试---" + id;
    }


    /**
     * 服务熔断
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "circuitBreakHandler", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),  //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //失败率达到多少后跳闸
    })
    @Override
    public String circuitBreak(Long id) {
        if (id < 0) {
            throw new RuntimeException("Id cannot less than zero!");
        }
        return Thread.currentThread().getName() + "-------" + id;
    }

    public String circuitBreakHandler(Long id) {
        return "服务熔断后fallback---> circuitBreakHandler: 当前服务不可用，请稍等重试！id = " + id;
    }
}
