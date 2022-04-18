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
    public void SendMessage(String message) {
        /**
         * 参数1 交换机名称
         * 参数2 routingkey
         * 参数3 发送的消息
         */
        amqpTemplate.convertAndSend("mq_boot_direct_01","mq_boot_direct_routingKey_01",message);
    }
}
