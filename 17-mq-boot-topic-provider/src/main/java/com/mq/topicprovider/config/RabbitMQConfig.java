package com.mq.topicprovider.config;


import org.springframework.amqp.core.*;
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
    // 保证交换机一定创建成功
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }
    // 保证交换机一定创建成功
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }
}
