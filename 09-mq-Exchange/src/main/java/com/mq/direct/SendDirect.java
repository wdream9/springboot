package com.mq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SendDirect {
    public static void main(String[] args) {

        // 获取链接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.153.124");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");

        // 获取链接
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            // 通道声明队列
            /**
             * 参数1 队列名
             * 参数2 是否为持久化链接
             * 参数3 链接是否排外，就是该队列是否只能被一个消费者监听
             * 参数4 是否自定删除，当队列中没有消息或者没有消费者链接时，队列会被自动删除
             * 参数5 队列的一些属性，通常为 null
             */
            channel.queueDeclare("myDirectQueue", true, false, false, null);

            // 声明一个交换机
            /**
             * 参数1 为交换机的名称取值任意
             * 参数2 为交换机的类型， 取值为direct、 fanout、 topic、 headers
             * 参数3 为是否为持久交换机
             *
             * 注意： 1、声明交换机时如果这个交换机已经存在则放弃声明，弱国交换机不存在则声明交换机
             * 2、这个代码可有可无，但是在使用前必须确保这个交换机被声明
             */
            channel.exchangeDeclare("directExchange", "direct", true);

            // 绑定一个交换机
            /**
             * 将队列绑定到交换机
             * 参数1 为队列的名称
             * 参数2 为交换机名称
             * 参数3 为消息的routingKey（就是BingKey）
             * 注意：
             *  1、在交换机与队列进行绑定的时候必须明确交换机和队列被申明
             */
            channel.queueBind("myDirectQueue", "directExchange", "directRoutingKey");
            String message = "这是精准播报的消息";

            /**
             * 发送消息到指定的队列
             * 参数1 为交换机的名称
             * 参数2 为消息的routingKey, 如果这个消息的routingKey和某个队列与交换机绑定的routingKey一致，那么这个消息就会发送到指定的队列中
             *
             * 注意：
             * 1、发送消息时必须确保交换机已经创建并且确保已经正确的绑定到某个队列
             */
            channel.basicPublish("directExchange", "directRoutingKey", null, message.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
