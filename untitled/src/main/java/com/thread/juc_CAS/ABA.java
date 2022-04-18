package com.thread.juc_CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABA {
    public static void main(String[] args) {

        AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(1,1);// AtomicStampedReference： 原子引用标志
        new Thread(()->{
            int stamp = reference.getStamp(); // 获取版本号
            System.out.println("a1==>" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(reference.compareAndSet(1, 2,
                    reference.getStamp(), reference.getStamp() + 1));
            System.out.println("a2==>" + reference.getStamp());
            System.out.println(reference.compareAndSet(2, 1,
                    reference.getStamp(), reference.getStamp() + 1));
            System.out.println("a3==>" + reference.getStamp());
        },"A").start();

        new Thread(()->{
            int stamp = reference.getStamp(); // 获取版本号
            System.out.println("b1==>" + reference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(reference.compareAndSet(1, 6,
                    reference.getStamp(), reference.getStamp() + 1));
            System.out.println("b2==>" + reference.getStamp());
        },"B").start();
    }
}
