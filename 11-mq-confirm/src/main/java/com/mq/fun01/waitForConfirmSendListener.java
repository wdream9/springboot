package com.mq.fun01;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class waitForConfirmSendListener {
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
            // 启动发送者 确认模式
            channel.confirmSelect();
            // 先开启异步监听
            channel.addConfirmListener(new ConfirmListener() {
                /**
                 * 消息确认以后的回调方法
                 * @param l 被确认的消息的编号，从1开始自动递增用于标记当前是第几条消息
                 * @param b 为当前消息是否同时确认了多个
                 * @throws IOException
                 */
                @Override
                public void handleAck(long l, boolean b) throws IOException {
                    System.out.println("消息确认---消息编号："+ l+ "---是否确认多条：" + b);
                }

                // 消息没有确认的回调方法
                // 这个方法被执行表示当前的消息没有被确认 需要进行消息补发
                // 参数1 为没有被确认的消息编号，
                // 参数2 为当前消息是否同时没有确认多个
                // 注意： 如果参数2 为true 则表示小于当前编号的所有消息可能都没有发送成功需要进行消息的补发
                        // 如果参数2 为false则表示当前编号的消息没发送成功需要进行补发
                @Override
                public void handleNack(long l, boolean b) throws IOException {
                    System.out.println("消息没有取认编号：" + l);
                }
            });
            // 开启异步监听发送消息
            for(int i =0;i< 10000;i++){
                channel.basicPublish("confirm_01_Exchange","confirm_01_RoutingKey",null,message.getBytes("utf-8"));
            }

        } catch (IOException e) {

        } catch (TimeoutException e) {
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
