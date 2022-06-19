package com.ityj.springcloud.service.impl;

import com.ityj.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@DefaultProperties(defaultFallback = "globalFallBackHandler", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
})
public class PaymentServiceImpl implements PaymentService {

    @Override
    @HystrixCommand
    public String success(Long id) {
        Double.valueOf("sf");
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

    public String globalFallBackHandler() {
        return Thread.currentThread().getName() + "全局兜底策略执行了。。。";
    }
}
