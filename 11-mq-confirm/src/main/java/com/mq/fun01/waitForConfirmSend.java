package com.mq.fun01;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class waitForConfirmSend {
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

            // 声明队列
            channel.queueDeclare("confirm_01_Queue",true,false,false,null);
            // 声明交换机
            channel.exchangeDeclare("confirm_01_Exchange","direct",true);
            // 绑定队列和交换机
            channel.queueBind("confirm_01_Queue","confirm_01_Exchange","confirm_01_RoutingKey");

            String message = "普通确认模式-01-的测试消息";

            channel.confirmSelect();
            channel.basicPublish("confirm_01_Exchange","confirm_01_RoutingKey",null,message.getBytes("utf-8"));
            /**
             * 阻塞线程等待服务返回响应，用于是否消息发送成功，如果服务确认消息已经发送完成则返回true 否则返货false
             * 可以为这个方法指定一个毫秒用于确定我们需要等待服务确认的超时时间，
             * 如果超过了指定的时间以后则会抛出异常InterruptedException 表示服务器出现问题了需要补发消息或
             * 将消息缓存到redis中稍后利用定时任务补发
             *
             * 无论是返回false 还是抛出异常消息都有可能发送成功有可能没有发送成功
             * 如果我们要求这个消息一定要发送到队列  列如订单数据，那怎么我们可以采用消息补发
             * 所谓补发 就是重新发送一次消息，可以使用递归或利用redis+定时任务来完成补发
             */
            boolean b = channel.waitForConfirms();

            System.out.println("消息发送成功：" + b);

        } catch (IOException e) {

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (channel!=null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if (connection!= null){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
