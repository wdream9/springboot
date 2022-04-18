package com.thread.study;


import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class UnsafeArrayList {
    public static void main(String[] args) {
//        ArrayList<String> strings = new ArrayList<>();
//        for (int i = 0; i < 10000; i++) {
//            new Thread(()->{
//                strings.add(Thread.currentThread().getName());
//            }).start();
//        }
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(strings.size());

        ReentrantLock lock = new ReentrantLock();
    }
}
