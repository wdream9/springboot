package com.thread.study;

public class porxy {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            Thread.currentThread().setName("son-1");
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + i);
            }
        });
        Thread.State state = thread.getState();
        System.out.println("thred state：" + state);
        thread.start();
        int i = 0;
    }
}
class Join implements Runnable{

    @Override
    public void run() {
        for (int i = 11; i < 20; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("vip: " + (i+1));
        }
    }
}
