package com.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class consumerRecive {
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

            channel.queueDeclare("confirm_01_Queue",true,false,false,null);
            channel.exchangeDeclare("confirm_01_Exchange","direct",true);
            channel.queueBind("confirm_01_Queue","confirm_01_Exchange","confirm_01_RoutingKey");

            // 开启事务
            channel.txSelect();
            /**
             * 手动接收消息
             * 参数2 为消息的确认机制，true表示自动消息确认，确认以后会从队列中被移除，当读取完消息以后就会自动确认
             *      如果为false，表示手动确认消息
             * 注意：
             *  1、如果我们只是接收的消息但是还没有来得处理，当前应用就会崩溃或在进行处理的时候例如像数据库中写数据但是数据库这时不可用，
             *  那么用于消息是自动确认的那么这个消息就会在接收完成以后自动从队列中被删除，这就会丢失消息
             */
            channel.basicConsume("confirm_01_Queue",false,"",new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                    /**
                     * 表示这个消息是否已经处理过
                     * true：表示已经被处理过
                     * false：表示没有被处理过
                     */
                    boolean flag = envelope.isRedeliver();
                    // 获取当前类部类中的通道
                    // 获取消息的编号，我们需要根据消息的编号来确认消息
                    long tag = envelope.getDeliveryTag();
                    Channel c = this.getChannel();
                    if (!flag){

                        String s = new String(body);
                        System.out.println("消费者--接收消息：" + s);

                        /**
                         * 手动确认消息，确认后表示当前消息已经成功处理了，需要从队列中进行移除，
                         * 这个方法当前消息的处理程序全部执行而完成以后执行，
                         * 参数1 为消息的序号
                         * 参数2 为是否确认多个；如果为true则表示需要确认小于等于当前编号的所有消息，false就是单个确认只确认当前消息
                         */
                        c.basicAck(tag,true);

                        /**
                         * 注意： 如果消费者开启了事务，而消息消费者是确认模式为手动确认那么必须要提交事务否则及时调用了确认方法
                         * 那么消息也不会从队列中被移除
                         */
                        c.txCommit();
                    }else {
                        // 表示消息已经被处理过，只需要确认消息就可以，不用其他处理
                        c.basicAck(tag,true);
                    }
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
