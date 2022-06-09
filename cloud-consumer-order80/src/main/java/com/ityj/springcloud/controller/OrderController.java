package com.ityj.springcloud.controller;

import com.ityj.springcloud.entity.dto.PaymentDTO;
import com.ityj.springcloud.entity.model.CommonResult;
import com.ityj.springcloud.entity.po.PaymentPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://localhost:8001/";

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<PaymentPO> getById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/save")
    public CommonResult<String> save(PaymentDTO paymentDTO) {
        log.info("Save: {}", paymentDTO);
        return restTemplate.postForObject(PAYMENT_URL + "payment/save", paymentDTO, CommonResult.class);
    }
}
