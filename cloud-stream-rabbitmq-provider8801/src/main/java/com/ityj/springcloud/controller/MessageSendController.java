package com.ityj.springcloud.controller;

import com.ityj.springcloud.service.MessageProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MessageSendController {

    @Autowired
    private MessageProviderService messageProviderService;

    @GetMapping("/sendMessage")
    public String sendMessage() {
        return messageProviderService.sendMessage();
    }

}
