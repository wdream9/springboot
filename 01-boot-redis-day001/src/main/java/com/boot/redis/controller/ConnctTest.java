package com.boot.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class ConnctTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/add")
    public String addToRedis(){
        ValueOperations redis = redisTemplate.opsForValue();
        redis.set("name","wyh");
        redis.set("age","123");
        return "redis添加数据成功";
    }
    @GetMapping("/get")
    public String getFromRedis(String name){
        ValueOperations redis = redisTemplate.opsForValue();
        return (String) redis.get("name");
    }
}
