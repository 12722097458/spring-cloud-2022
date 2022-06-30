package com.ityj.springcloud.controller;

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

    @GetMapping("/nacos/{id}")
    public String getPortInfo(@PathVariable("id") Integer id) {
        return "Input parameter id:" + id + ",serverPort:" + serverPort;
    }

}

