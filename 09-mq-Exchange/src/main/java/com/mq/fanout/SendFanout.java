package com.mq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SendFanout {
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

            channel.exchangeDeclare("fanoutExchange", "fanout", true);
            String message = "fanout生产者发布的消息";

            channel.basicPublish("fanoutExchange", "", null, message.getBytes("utf-8"));
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
