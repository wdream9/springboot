package com.mq.topicprovider.service.impl;

import com.mq.topicprovider.service.SendService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "SendService")
public class SendServiceImpl implements SendService {
    @Autowired
    private AmqpTemplate amqpTemplate;


    @Override
    public void topicSendMessage(String message) {
        amqpTemplate.convertAndSend("topicExchange","aa.bb", message);
    }
}
