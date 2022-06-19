package com.ityj.springcloud.controller;

import com.ityj.springcloud.entity.model.CommonResult;
import com.ityj.springcloud.service.PaymentFeignService;
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
    public CommonResult<String> timeout(@PathVariable("id") Long id) {
        return paymentFeignService.timeout(id);
    }

}
