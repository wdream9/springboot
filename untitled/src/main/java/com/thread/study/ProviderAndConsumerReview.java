package com.thread.study;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ProviderAndConsumerReview {
    public static void main(String[] args) {
        /*new ThreadPoolExecutor(10, 30, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        })*/
    }
}


