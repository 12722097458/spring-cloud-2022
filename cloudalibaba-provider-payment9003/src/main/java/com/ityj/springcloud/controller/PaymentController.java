package com.ityj.springcloud.controller;

import com.ityj.springcloud.entity.model.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/get/{id}")
    public CommonResult<String> getPaymentInfo(@PathVariable("id") Integer id) {
        if (id == 3) {
            throw new IllegalArgumentException("非法参数3，IllegalArgumentException");
        }
        return CommonResult.success("Input parameter id:" + id + ",serverPort:" + serverPort);
    }

}

