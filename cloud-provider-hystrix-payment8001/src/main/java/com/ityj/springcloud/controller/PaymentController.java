package com.ityj.springcloud.controller;

import com.ityj.springcloud.entity.model.CommonResult;
import com.ityj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/success/{id}")
    public CommonResult<String> success(@PathVariable("id") Long id) {
        String result = paymentService.success(id);
        log.info(result);
        return CommonResult.success(result);
    }

    @GetMapping("/timeout/{id}")
    public CommonResult<String> timeout(@PathVariable("id") Long id) {
        String result = paymentService.timeout(id);
        log.info(result);
        return CommonResult.success(result);
    }

    @GetMapping("/circuitBreak/{id}")
    public CommonResult<String> circuitBreak(@PathVariable("id") Long id) {
        String result = paymentService.circuitBreak(id);
        return CommonResult.success(result);
    }

}
