package com.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    public static void main(String[] args) {
        /**
         * 创建一个连接工厂
         */
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
            /**
             * 声明一个队列
             * 参数1 为队列名取值任意
             * 参数2 是否为持久化的队列
             * 参数3 受否排外，如果排外则这个队列只允许一个消费者监听
             * 参数4 是否自动删除，如果为true则表示当队列中没有消息，也没有消费者链接时就会自动删除这个队列
             * 参数5 为队列的一些属性设置通常为null即可
             *
             * 注意：1、申明队列时，这个独队列名称如果已经存在则放弃声明，如果队列不存在则会声明一个新的队列
             * 2、队列可以取值任意，但是要与消息接收时完全一致
             * 3、这行代码是可有可无的，但是一定要在发送消失前确认队列名已经存在在Rabbitmq中，否则就会抛出异常
             *
             * Declare：声明
             */
            channel.queueDeclare("",true,false,false,null);
            String message = "这是我的sadasdfasdf测试消息asdasdfasdfasasdfasdffd";

            /**
             * 发送消息到队列MQ
             * 参数1 是交换机名称，这里为空字符串表示不使用交换机
             * 参数2 为队列名或routingKey，当指定了交换机名称以后这个值就是routingKey
             * 参数3 为消息属性信息，通常为空即可
             * 参数4 为具体的消息数据的字节数组
             */
            channel.basicPublish("","myQueue",null,message.getBytes("utf-8"));
        }catch (IOException e){

        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            if (channel!=null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
