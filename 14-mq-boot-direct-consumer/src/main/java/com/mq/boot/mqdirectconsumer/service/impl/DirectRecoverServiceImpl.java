package com.mq.boot.mqdirectconsumer.service.impl;

import com.mq.boot.mqdirectconsumer.service.DirectRecoverService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "DirectRecoverService")
public class DirectRecoverServiceImpl implements DirectRecoverService {
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

    /**
     * @RabbitListener 用于标记当前方法是一个RabbitMQ的消息监听方法，作用是持续性的自动接收消息
     * 这个方法不需要手动调用，Spring会自动运行这个监听
     * queues: 用于指定一个已经存在的队列名.
     * 注意： 如果当前监听方法正常结束Spring就会自动确认消息，如果出现异常则不会确认消息，
     * 因此在消息处理时我们需要做好消息的 重复处理工作
     * @param message   接接收到的具体的消息数据
     *
     */
    @Override
    @RabbitListener(queues = {"mq_boot_queue_01"})
    public void directReceive(String message) {
        System.out.println("消费者监听消息：" + message);
//        System.out.println(10/0);
    }

}
