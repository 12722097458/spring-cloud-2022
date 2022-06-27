package com.ityj.springcloud.service.impl;

import cn.hutool.core.lang.UUID;
import com.ityj.springcloud.service.MessageProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Source.class)  //定义消息的推送管道
@Slf4j
public class MessageProviderServiceImpl implements MessageProviderService {

    @Qualifier("output")
    @Autowired
    private MessageChannel messageChannel;

    @Override
    public String sendMessage() {
        String message = UUID.fastUUID().toString();
        log.info("Sending data {} to RabbitMQ...", message);
        messageChannel.send(MessageBuilder.withPayload(message).build());
        return message;
    }
}
