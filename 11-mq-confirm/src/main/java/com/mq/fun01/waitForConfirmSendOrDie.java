package com.mq.fun01;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class waitForConfirmSendOrDie {
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
             * 批量消息确认
             * 它会同时向服务器确认当前通道中所有的消息是否已经全部写入成功
             * 这个方法没有任何的返回值，如果服务器中有一条消息没有能够成功或向服务器发送确认时服务器不可访问都被认定为消息确认失败，可能有消息没有发送成功
             * 如果无法向服务器获取确认消息那么方法就会抛出InterruptedException异常，这是就需要补发消息到队列
             * waitforconfirmordie方法可以指定一个参数timeout 用于等带服务器的确认时间，如果超过这个时间也会抛出异常，表示确认失败需要补发消息
             *
             * 注意：
             *      批量消息确认的速度比普通的消息确认要快，但是如果一旦出现消息补发的情况，我们不能确定具体那条消息没有完成发送，需要将本次的发送的所有消息全部补发
             */
            channel.waitForConfirmsOrDie();
            System.out.println("消息发送成功：");

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
