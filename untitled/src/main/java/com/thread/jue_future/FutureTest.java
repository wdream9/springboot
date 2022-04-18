package com.thread.jue_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 没有返回值的异步回调
         */
        // 发起一个请求
        /*CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "runAsync==>Void");
        });
        System.out.println("111111");
        completableFuture.get();// 获取执行吗结果*/

        /**
         * 有返回值的异步回调
         */
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync===>Integer");
            int i = 10/0;
            return 1024;
        });
        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println("t:" + t);  // 正常的返回结果
            System.out.println("u:" + u);  // 失败的返回结果

        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return 233;
        }).get());
    }
}
