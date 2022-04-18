package com.boot.dubbo.service.impl;

import com.boot.dubbo.service.AsyncService;
import io.netty.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class AsyncServiceImpl implements AsyncService {
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Async
    @Override
    public void sendMs() {
        System.out.println("喀什大幅度");
        // 业务的一些其他操作，保存业务数据
    }
}
