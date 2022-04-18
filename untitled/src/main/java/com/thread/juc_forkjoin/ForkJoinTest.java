package com.thread.juc_forkjoin;

import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinTest {
    private static int a;


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();   //执行时间：32966
//        test2();
        test3();   //执行时间：1504
    }
    public static void test1(){
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = 1;i <= 100_0000_0000L;i++){
            sum+=i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "执行时间：" + (end - start));
    }
    public static void test2() throws ExecutionException, InterruptedException {

        // 双端队列，窃取任务
        long start = System.currentTimeMillis();
        ForkJoinPool fork = new ForkJoinPool();
        ForkJoinDemo task = new ForkJoinDemo(0L,100_0000_0000L);
        ForkJoinTask<Long> submit = fork.submit(task);   // 提交任务
        Long aLong = submit.get();

        long end = System.currentTimeMillis();
        System.out.println("sum=" + aLong + "执行时间：" + (end - start));
    }
    public static void test3(){

        // 并行流
        long start = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0, 100).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum=" + reduce + "执行时间：" + (end - start));
    }
}
