package com.mq.topicconsumer.service.impl;

import com.mq.topicconsumer.service.TopicRecoverService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "TopicRecoverService")
public class TopicRecoverServiceImpl implements TopicRecoverService {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    @RabbitListener(bindings = {@QueueBinding( // @QueueBinding 注解完成队列和交换机的绑定
            value = @Queue("topic01"),  //  @Queue 创建一个队列（没有参数表示创建一个随机队列）
            key = {"aa"},  // routingkey
            exchange = @Exchange(
                    name="topicExchange",
                    type = "topic"))})
    public void topicReceive01(String message) {
        System.out.println("topic消费者监听消息---topicReceive01---aa：" + message);
    }

    @Override
    @RabbitListener(bindings = {@QueueBinding( // @QueueBinding 注解完成队列和交换机的绑定
            value = @Queue("topic02"),  //  @Queue 创建一个队列（没有参数表示创建一个随机队列）
            key = {"aa.*"},  // routingkey
            exchange = @Exchange(
                    name="topicExchange",
                    type = "topic"))})
    public void topicReceive02(String message) {
        System.out.println("topic消费者监听消息---topicReceive02 --aa.*：" + message);
    }

    @Override
    @RabbitListener(bindings = {@QueueBinding( // @QueueBinding 注解完成队列和交换机的绑定
            value = @Queue("topic03"),  //  @Queue 创建一个队列（没有参数表示创建一个随机队列）
            key = {"aa.#"},  // routingkey
            exchange = @Exchange(
                    name="topicExchange",
                    type = "topic"))})
    public void topicReceive03(String message) {
        System.out.println("topic消费者监听消息---topicReceive03 --aa.#：" + message);
    }

}
