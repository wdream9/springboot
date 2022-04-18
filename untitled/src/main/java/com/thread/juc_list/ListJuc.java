package com.thread.juc_list;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListJuc {
    public static void main(String[] args) {
        /**
         * CopyOnWriteArrayList：写入时复制 COW 计算机程序设计领域的一种优化策略；
         *                      多个线程调用时，list，读取的时候，固定的，写入（覆盖）
         *                      再写入的时候避免覆盖，造成数据问题
         *
         *                      读写分离思想
         */
        CopyOnWriteArrayList<String> listq = new CopyOnWriteArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            // 开启线程执行
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },"线程List").start();
        }
    }
}
