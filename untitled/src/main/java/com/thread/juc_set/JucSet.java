package com.thread.juc_set;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class JucSet {
    public static void main(String[] args) {
//        HashSet<String> hash = new HashSet<>();
//        Set<String> hash = Collections.synchronizedSet(new HashSet<>());
        CopyOnWriteArraySet<String> strings = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 50; i++) {
            // 开启线程执行
            new Thread(()->{
                strings.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(strings);
            },"set").start();
        }
    }
}
