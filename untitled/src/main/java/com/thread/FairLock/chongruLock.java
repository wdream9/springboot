package com.thread.FairLock;

public class chongruLock {
    public static void main(String[] args) {
        /**
         * synchronized版本重入锁
         */
        PhoneSync phone = new PhoneSync();

        new Thread(()->{
            phone.send();
        },"A").start();
        new Thread(()->{
            phone.send();
        },"B").start();

    }
}
class PhoneSync{

    public synchronized void send(){

        System.out.println(Thread.currentThread().getName() + "send");
        call();
    }

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName() + "call");
    }
}
class PhoneLock{
    public synchronized void send(){

        System.out.println(Thread.currentThread().getName() + "send");
        call();
    }

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName() + "call");
    }
}
