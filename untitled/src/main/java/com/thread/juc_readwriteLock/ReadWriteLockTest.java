package com.thread.juc_readwriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public static void main(String[] args) {
        SourceData sourceData = new SourceData();

        // 写入
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                sourceData.put(temp + "",temp + "");
            },String.valueOf(i)).start();
        }
        // 读取
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                sourceData.get(temp + "");
            },String.valueOf(i)).start();
        }

    }
}

class SourceData{
    /**
     * 自定义缓存
     */
    private volatile Map<String,Object> map = new HashMap<>();

    /**
     * 读写锁，更加细腻度的的控制
     */
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    // 存，写
    public void put(String str,Object value){
        // 加上写锁
        lock.writeLock().lock();
        try {

            System.out.println(Thread.currentThread().getName() + "开始写入");
            map.put(str,value);
            System.out.println(Thread.currentThread().getName() + "写入ok");
        }catch (Exception e){

        }finally {
            lock.writeLock().unlock();
        }
    }

    // 取，读
    public void get(String key){
        lock.readLock().lock();
        try {

            System.out.println(Thread.currentThread().getName() + "读取key" + map.get(key));
        }catch (Exception e){

        }finally {
            lock.readLock().unlock();
        }

    }
}
