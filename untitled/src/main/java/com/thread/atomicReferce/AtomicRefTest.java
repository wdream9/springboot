package com.thread.atomicReferce;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicRefTest {

    public static void main(String[] args) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("",null);

    }
}

class AtomicRef{
    AtomicReference<Thread> reference = new AtomicReference<>();


    // 加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "====>myLock");

        // 自旋锁
        while (!reference.compareAndSet(null,thread)){

        }
    }
    // 解锁
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        reference.compareAndSet(thread,null);
    }
}
