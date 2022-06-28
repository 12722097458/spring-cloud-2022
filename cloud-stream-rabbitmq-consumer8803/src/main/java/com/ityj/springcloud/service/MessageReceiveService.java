package com.ityj.springcloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@EnableBinding(Sink.class)
@Component
@Slf4j
public class MessageReceiveService {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(value = Sink.INPUT)
    public void receive(Message<String> message) {
        log.info("Receive data {}, Server port is:{}", message.getPayload(), serverPort);
    }
}
