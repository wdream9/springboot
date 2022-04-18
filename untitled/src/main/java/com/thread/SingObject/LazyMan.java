package com.thread.SingObject;


// 懒汉式单列
public class LazyMan {
    private static LazyMan lazyMan;

    // 双重检测锁模式的  懒汉时单列   DCL懒汉式
    public static LazyMan getInstance(){
        if ( lazyMan == null){
            synchronized (LazyMan.class){
                if (lazyMan == null){
                    lazyMan = new LazyMan();
                    // 不是原子性操作
                    /**
                     * 1、分配内存空间
                     * 2、执行构造方法，初始化对象
                     * 3、把这个对象指向这个空间
                     */
                }
            }
        }
        return lazyMan;
    }
}
