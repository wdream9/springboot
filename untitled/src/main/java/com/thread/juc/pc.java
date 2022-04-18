package com.thread.juc;

/**
 * 演示消费者和生产者的 虚假唤醒 问题
 * 产生原因：因为if只会执行一次，执行完会接着向下执行if（）外边的
 *          而while不会，直到条件满足才会向下执行while（）外边的
 * 解决：if换成 while
 */
public class pc {
    public static void main(String[] args) {
        Static aStatic = new Static();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                aStatic.decrment();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                aStatic.incrment();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                aStatic.decrment();
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                aStatic.incrment();
            }
        }, "D").start();

    }
}

/**
 * 共享资源对象，在并发编程中把共享资源搞成一个对象，对外暴露出统一的操作方法
 */
class Static {
    int number = 0;

    synchronized void incrment() {
        if (number != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;
        System.out.println("number-"+ Thread.currentThread().getName()+": "+ number);
        this.notifyAll();
    }

    synchronized void decrment() {
        if (number == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number--;
        System.out.println("number-"+ Thread.currentThread().getName()+": "+ number);
        this.notifyAll();

    }
}
