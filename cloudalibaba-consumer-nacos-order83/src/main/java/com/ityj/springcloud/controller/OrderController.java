package com.ityj.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://nacos-payment-provider/";

    @GetMapping("/consumer/payment/nacos/{id}")
    public String getById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "payment/nacos/" + id, String.class);
    }

}
