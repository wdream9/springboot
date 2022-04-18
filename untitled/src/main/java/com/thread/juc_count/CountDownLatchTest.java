package com.thread.juc_count;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * countDownLatch :减法计数器
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        // 总数是10，通常用在必须要执行任务的时候，再使用；
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();// 数量-1
            }).start();
        }
        try {
            /**
             * 等待计数器归0，然后才向下执行
             * 等待这10个线程执行完成了才向下执行任务
             */
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("closr door");
    }
}
