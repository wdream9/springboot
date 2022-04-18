package com.thread.juc;

import java.awt.print.Printable;
import java.time.LocalDate;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class pc_juc {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                data.decrment();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                data.incrment();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                data.decrment();
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                data.incrment();
            }
        }, "D").start();
    }
}

class Data {
    int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


     void incrment() {
         lock.lock();
         try {
             // 业务代码
             while (number != 0){
                 condition.await();
             }
             number++;
             System.out.println("number-"+ Thread.currentThread().getName()+": "+ number);
             condition.signalAll();
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             lock.unlock();
         }
    }

     void decrment() {
         lock.lock();
         try {
             // 业务代码
             while (number == 0){
                 condition.await();
             }
             number--;
             System.out.println("number-"+ Thread.currentThread().getName()+": "+ number);
             condition.signal();
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             lock.unlock();
         }

    }
}
