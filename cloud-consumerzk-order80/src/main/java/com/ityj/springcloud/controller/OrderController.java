package com.ityj.springcloud.controller;

import com.ityj.springcloud.entity.dto.PaymentDTO;
import com.ityj.springcloud.entity.model.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://cloud-payment-service/";   // zookeeper注意大小写

    @GetMapping("/consumer/zk/payment/get/{id}")
    public CommonResult<PaymentDTO> getById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "zk/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/zk/payment/save")
    public CommonResult<String> save(PaymentDTO paymentDTO) {
        log.info("Save: {}", paymentDTO);
        return restTemplate.postForObject(PAYMENT_URL + "zk/payment/save", paymentDTO, CommonResult.class);
    }
}
