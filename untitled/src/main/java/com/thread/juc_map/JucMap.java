package com.thread.juc_map;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class JucMap {
    public static void main(String[] args) {
        /**
         * map 是这样用吗？ new hashmap
         * hashMap默认等价于什么？
         */
        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>(16, 0.75F);
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            new Thread(()->{
                concurrentHashMap.put(String.valueOf(finalI), UUID.randomUUID().toString().substring(0,5));
            }).start();
        }

    }
}
