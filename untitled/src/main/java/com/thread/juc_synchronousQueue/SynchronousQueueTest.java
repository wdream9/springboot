package com.thread.juc_synchronousQueue;

import com.sun.tools.javac.Main;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        // 开启线程put元素
        new Thread(()->{
            try {
                System.out.println("put==>"+Thread.currentThread().getName() + ("==>1"));
                queue.put("1");
                Thread.sleep(2000);
                System.out.println("put==>"+Thread.currentThread().getName() + ("==>2"));
                queue.put("2");
                Thread.sleep(2000);
                System.out.println("put==>"+Thread.currentThread().getName() + ("==>3"));
                queue.put("3");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                System.out.println("take===>"+Thread.currentThread().getName() + queue.take());
                Thread.sleep(2000);
                System.out.println("take===>"+Thread.currentThread().getName() + queue.take());
                Thread.sleep(2000);
                System.out.println("take===>"+Thread.currentThread().getName() + queue.take());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
