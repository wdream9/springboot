package com.thread.juc_executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {
    public static void main(String[] args) {
//        ExecutorService threadpool = Executors.newSingleThreadExecutor(); // 单线程进程池

//        ExecutorService threadpool = Executors.newFixedThreadPool(5);  // 固定大小线程池

        ExecutorService threadpool = Executors.newCachedThreadPool(); // 缓存线程池，可以伸缩的，遇强则强，遇弱则弱
        for (int i = 0; i < 100; i++) {
            threadpool.execute(()->{
                System.out.println(Thread.currentThread().getName());

            });

        }
        threadpool.shutdown();
    }
}
