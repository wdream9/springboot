package com.thread.juc_blockingQueue;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        // 报错
//        test1();
        // 不报错
//        test2();

        // 阻塞一直等待
//        test3();

        // 阻塞等待，超时
        test4();
    }
    /**
     * 队列满了抛出异常
     * 队列取完后再取会抛出异常
     */
    public static void test1(){
        ArrayBlockingQueue<Object> objects = new ArrayBlockingQueue<>(3);
        // 往队列添加元素
        System.out.println(objects.add(1));
        System.out.println(objects.add(2));
        System.out.println(objects.add(3));
        // 在添加超出队列的长度会报错
        // Exception in thread "main" java.lang.IllegalStateException: Queue full
        // System.out.println(objects.add(4));

        // 查看队首元素
        // system.out.println(objects.element());
        System.out.println(objects.peek());
        System.out.println("============================");
        // 取元素
        System.out.println(objects.remove());
        System.out.println(objects.remove());
        System.out.println(objects.remove());

        // 队列已经被取空了，再取就会报错
        // Exception in thread "main" java.util.NoSuchElementException
//        System.out.println(objects.remove());

    }

    /**
     *  不抛出异常
     */
    public static void test2(){
        ArrayBlockingQueue<Object> o = new ArrayBlockingQueue<>(3);

        System.out.println(o.offer(1));
        System.out.println(o.offer(2));
        System.out.println(o.offer(3));
        // 在添加超出队列的长度不会报错，会返回false
        System.out.println(o.offer(4));

        System.out.println("===============");
        // 从队列取元素
        System.out.println(o.poll());
        System.out.println(o.poll());
        System.out.println(o.poll());

        // 队列没有元素再次移除返回  null
        System.out.println(o.poll());

    }

    /**
     * 等待阻塞(一直阻塞)
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue<Object> objects = new ArrayBlockingQueue<>(3);
        // 添加元素
        objects.put(1);
        objects.put(2);
        objects.put(3);

        // 多添加元素会发生阻塞
//        objects.put(4);
        // 拿取元素
        System.out.println(objects.take());
        System.out.println(objects.take());
        System.out.println(objects.take());

        // 队列元素已经被取完了，现在就会阻塞等待，不会发生报错
        System.out.println(objects.take());


    };


    /**
     * 等待阻塞（等待超时）,然后就退出
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue<Object> objects = new ArrayBlockingQueue<>(3);
        objects.offer(1);
        objects.offer(1);
        objects.offer(1);

        // 队列已满在添加就会等待，超时后就退出
        objects.offer(1, 1,TimeUnit.SECONDS);

        objects.poll();
        objects.poll();
        objects.poll();
        objects.poll(2,TimeUnit.SECONDS);

    }
}
