package com.thread.JMM_volatile;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileAtom {
    private volatile static AtomicInteger num1 = new AtomicInteger(0);
    public static void add(){
//        num1++;
//        num1.getAndIncrement();

    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int i1 = 0; i1 < 1000; i1++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(num1);
    }
}
