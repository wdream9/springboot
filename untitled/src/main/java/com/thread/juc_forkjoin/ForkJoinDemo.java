package com.thread.juc_forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;


// recursiveTask 递归任务
public class ForkJoinDemo extends RecursiveTask<Long> {
    private long start;
    private long end;
    private final long temp = 10000L;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum+=i;
            }
            return sum;
        }else { // forjoin
            long middle = (start + end) / 2; //中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork(); // 拆分任务，把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, middle);
            task2.fork();  // 拆分任务，将任务压入线程

            return task1.join() + task2.join();
        }
    }
}
