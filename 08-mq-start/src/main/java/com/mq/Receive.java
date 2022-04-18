package com.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receive {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.153.124");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = null;
        Channel channel = null;

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare("myQueue",true,false,false,null);
            /**
             * 接收消息
             * 参数1 当前消费者监听的队列名称，队列名称必须与发布消息是的队列名称相同，否者接收不到消息
             * 参数2 消息是否自动确认，true表示自动确认接受完消息以后会自动将消息从队列移除
             * 参数3 为消息接收者的标签，用于多个消费者监听同一个队列时用于确认不同消费者，通常为空
             * 参数4 为消息接收的回调方法这个方法中具体完成对消息的处理码
             *
             * 注意： 使用了basicConsume方法以后，会启动一个线程在持续监听的队列，如果队列中有信息的数据进入则会自动接收消息
             *  ，因此不能关闭链接和通道对象
             */
            channel.basicConsume("myQueue",true,"",new DefaultConsumer(channel){
                // 消息的具体接收和处理方法
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "utf-8");
                    System.out.println("消费者接收到消息：" + message);
                }
            });

        }catch (IOException e){

        }catch (TimeoutException e){

        }finally {

        }
    }
}
