package com.thread.study;

import javax.xml.stream.events.ProcessingInstruction;
import java.awt.*;

public class ProviderAadConsumer {
    public static void main(String[] args) {
        SyncContainer syncContainer = new SyncContainer();
        Provider provider = new Provider(syncContainer);
        Consumer consumer = new Consumer(syncContainer);
        provider.start();
        consumer.start();
    }

}

// 生产者
class Provider extends Thread{
    SyncContainer container;
    public Provider(SyncContainer syncContainer){
        this.container = syncContainer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("生产了" + i+ "只鸡");
            container.push(new Chicken(i));
        }
    }
}

// 消费者
class Consumer extends Thread{
    private SyncContainer syncContainer;
    public Consumer(SyncContainer container){
        this.syncContainer = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了第" + syncContainer.pop().getI() + "只鸡");
        }
    }
}

// 产品
class Chicken{
    int i;
    public Chicken(int i){
        this.i= i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}

// 缓冲区
class SyncContainer{
    // 需要一个容器（仓库）来存储产品
    Chicken[] chickens = new Chicken[10];

    // 容器计数器
    int count = 0;
    // 生产者放入产品到容器
    public synchronized void push(Chicken chicken){
        if (count == chickens.length){
            // 通知消费者消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 如果没有满，我们需要丢入产品
        chickens[count] = chicken;
        count++;
        // 可以通知消费者消费了
        this.notifyAll();
    }
    // 消费者消费产品
    public synchronized Chicken pop()  {
        // 判断是否能消费
        if(count == 0){
            // 等待生产者生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        Chicken chicken = chickens[count];

        // 吃完了，通知生产者进行生产
        this.notifyAll();
        return chicken;
    }

}
