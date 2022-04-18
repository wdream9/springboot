package com.thread.juc_executors;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadpoolExecutorTest {

    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(7),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()

        );
        for (int i = 1; i < 20; i++) {
            executor.execute(()->{
                System.out.println(Thread.currentThread().getName() + "---OK");
            });
        }

    }
}
