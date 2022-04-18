package com.mq.boot.fanoutprovider.service.impl;

import com.mq.boot.fanoutprovider.service.SendService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "SendService")
public class SendServiceImpl implements SendService {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override

    public void FanoutSendMessage(String message) {
        amqpTemplate.convertAndSend("fanoutExchange","",message);
    }
}
