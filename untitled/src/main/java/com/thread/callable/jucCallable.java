package com.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class jucCallable {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask(myThread);
        /*new Thread(()->{
            try {
                myThread.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();*/
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();  // 执行两次但是，只输出一个，因为结果会被缓存

        // 获取callable返回值
        try {
            String o = (String)futureTask.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
class MyThread implements Callable {

    @Override
    public String call() throws Exception {
        return "call";
    }
}
