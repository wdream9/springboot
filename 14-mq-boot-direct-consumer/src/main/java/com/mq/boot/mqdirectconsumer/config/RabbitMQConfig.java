package com.mq.boot.mqdirectconsumer.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("mq_boot_direct_01");
    }
    @Bean
    public Queue directQueue(){
        return new Queue("mq_boot_queue_01");
    }
    @Bean
    public Binding directBinding(Queue directQueue, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue).to(directExchange).with("mq_boot_direct_routingKey_01");
    }
}
