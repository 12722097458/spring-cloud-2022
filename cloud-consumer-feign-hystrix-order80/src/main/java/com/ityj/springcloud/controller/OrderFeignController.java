package com.ityj.springcloud.controller;

import com.ityj.springcloud.entity.model.CommonResult;
import com.ityj.springcloud.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hystrix")
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/success/{id}")
    public CommonResult<String> success(@PathVariable("id") Long id) {
        return paymentFeignService.success(id);
    }

    @GetMapping("/consumer/payment/timeout/{id}")
    @HystrixCommand(fallbackMethod = "orderTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    public CommonResult<String> timeout(@PathVariable("id") Long id) {
        //Double.valueOf(null);
        return paymentFeignService.timeout(id);
    }

    public CommonResult<String> orderTimeoutHandler(Long id) {
        return CommonResult.fail("orderTimeoutHandler: 80消费者端无法在规定时间内获取到响应数据或者程序出错！id = " + id);
    }

}
