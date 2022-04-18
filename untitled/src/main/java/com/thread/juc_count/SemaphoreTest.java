package com.thread.juc_count;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * semaphore（生莫废尔）：信号量
 */
public class SemaphoreTest {
    public static void main(String[] args) {

        // 线程数量: 停车位，限流用得比较多
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                // 每个 acquire 都会阻塞
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }

                // release()  释放
            }).start();
        }
    }
}
