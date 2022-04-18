package com.starter;

import org.redisson.api.RedissonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class AppRun {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext run = SpringApplication.run(AppRun.class, args);
        RedissonClient redissonClient =(RedissonClient) run.getBean("redissonClient");
        byte[] arr = new byte[1024];
        int read = System.in.read(arr);
        System.out.println(read);
    }
}
