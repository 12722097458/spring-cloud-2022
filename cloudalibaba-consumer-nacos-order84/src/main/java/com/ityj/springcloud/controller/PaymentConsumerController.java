package com.ityj.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ityj.springcloud.entity.model.CommonResult;
import com.ityj.springcloud.handler.CustomerBlockHandler;
import com.ityj.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
public class PaymentConsumerController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @SentinelResource(value = "payment/get/{id}",
            blockHandlerClass = CustomerBlockHandler.class, blockHandler = "getPaymentInfo_blockHandler")
    @GetMapping("/payment/get/{id}")
    public CommonResult<String> getPaymentInfo(@PathVariable("id") Integer id) {
        return paymentFeignService.getPaymentInfo(id);

    }

}
