package com.thread.JMM_volatile;

import java.util.concurrent.TimeUnit;

public class VolatileTest {

    private volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            while (num == 0) {
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);
        /**
         * main线程执行完成，更改了主内存中的值，但是另外一个线程不知到主内存中num值发生了改变（线程没有监听也没有轮询主内存），
         * 没有人通知另外一个线程num发生改变了，
         * 所以另外一个线程一直使用的是拷贝过去的num=0，所以一直在做死循环
         */
        num = 1;
        System.out.println(num);
    }
}
