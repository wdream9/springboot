package com.thread.juc_CAS;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo1 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        atomicInteger.getAndIncrement();
    }
}
