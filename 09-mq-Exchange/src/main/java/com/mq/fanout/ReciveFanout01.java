package com.mq.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ReciveFanout01 {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.153.124");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection =null;
        Channel channel = null;

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            /**
             * 由于fanout是采用的广播模式，，它不需要绑定routingKey，而又可能会有很多个的消费者类接受这个
             * 交换机中的数据，因此我们创建队列时要创建一个随机的队列名称
             *
             * 没有参数的queueDeclare方法会创建一个名称随机的队列
             * 这个队列的数据 是非持久化、排外的（同时最多只能一个消费者监听当前队列）、是自动删除的，就是队列没有数据时，或没有消费者监听时 这个队列会自动删除
             *
             * getQueue()方法用于这个随机的队列名
             */
            String queueName = channel.queueDeclare().getQueue();
            channel.exchangeDeclare("fanoutExchange","fanout",true);
            channel.queueBind(queueName,"fanoutExchange","");

            /**
             *  监听队列
             */
            channel.basicConsume(queueName,true,"",new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String s = new String(body);
                    System.out.println("接收消息-01：" + s);
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
