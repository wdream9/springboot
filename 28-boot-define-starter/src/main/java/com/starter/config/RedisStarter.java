package com.starter.config;

import jodd.util.StringUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnClass(value = Redisson.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisStarter {
    @Autowired
    private RedisProperties redisProperties;

    @Bean
    @ConditionalOnClass
    RedissonClient redissonClient(RedisProperties redisProperties){
        Config config = new Config();
        String prefix = "redis://";
        SingleServerConfig singleServerConfig = config.useSingleServer().setAddress(prefix + redisProperties.getHost() + ":" + redisProperties.getPort()).setIdleConnectionTimeout(redisProperties.getTimeout());
        if (!StringUtil.isEmpty(redisProperties.getPassword())){
            singleServerConfig.setPassword(redisProperties.getPassword());
        }
        return Redisson.create(config);
    }
}
