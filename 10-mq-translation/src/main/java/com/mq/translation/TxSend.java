package com.mq.translation;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.swing.event.ChangeEvent;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TxSend {
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
            channel.queueDeclare("txQueue",true,false,false,null);
            // 声明交换机
            channel.exchangeDeclare("txExchange","direct",true);
            // 绑定队列和交换机
            channel.queueBind("txQueue","txExchange","txRoutingKey");

            String message = "事务的测试消息";
            /**
             * 启动一个事务，启动事务以后所有写入到队列中的消息
             * 必须显示的调用txCommit()提交事务或txRollback()回滚事务
             */
            channel.txSelect();
            channel.basicPublish("txExchange","txRoutingKey",null,message.getBytes("utf-8"));

            /**
             * 提交事务，如果我们调用了channel.txSelect();，方法启动了事务，那么必须显示调用事务的提交，
             * 否则消息不会真正的写入到队列，提交时以后会将内存中的消息写入到队列并释放内存
             */
            channel.txCommit();

        } catch (IOException e) {

        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        finally {
            if (channel!=null){
                try {
                    channel.txRollback();
                    try {
                        channel.close();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
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
