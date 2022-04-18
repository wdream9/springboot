package com.mq.fanoutconsumer.service.impl;

import com.mq.fanoutconsumer.service.FanoutRecoverService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "FanoutRecoverService")
public class FanoutRecoverServiceImpl implements FanoutRecoverService {
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 每执行一次这个方法只能接收一次消息，如果有新消息进入则不会自动接收消息，
     * 不建议使用
     */
    @Override
    public void receive() {
        String message =(String) amqpTemplate.receiveAndConvert("mq_boot_queue_01");
        System.out.println("消费者接收消息：" + message);
    }

    @Override
    @RabbitListener(bindings = {@QueueBinding( // @QueueBinding 注解完成队列和交换机的绑定
            value = @Queue,  //  @Queue 创建一个队列（没有参数表示创建一个随机队列）
            exchange = @Exchange(
                    name="fanoutExchange",
                    type = "fanout"))})
    public void fanoutReceive01(String message) {
        System.out.println("fanout消费者监听消息--fanoutReceive01：" + message);
    }

    @Override
    @RabbitListener(bindings = {@QueueBinding( // @QueueBinding 注解完成队列和交换机的绑定
            value = @Queue,  //  @Queue 创建一个队列（没有参数表示创建一个随机队列）
            exchange = @Exchange(
                    name="fanoutExchange",
                    type = "fanout"))})
    public void fanoutReceive02(String message) {
        System.out.println("fanout消费者监听消息---fanoutReceive02：" + message);
    }

}
