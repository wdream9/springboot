package com.thread.juc_list;

import java.util.HashMap;

public class SetJUC {
    public static void main(String[] args) {

        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            final int temp = i;
            new Thread(()->{
                map.put(temp+"",temp);
            }).start();
        }
        while(Thread.activeCount() == 2){
            System.out.println(map.toString());
        }
    }
}
