package com.thread.JMM_volatile;

public class Atom {
    private static int x;
    private static Long num;
    public static void main(String[] args) {
        System.out.println(x);
    }
    public static void add(){
        x = 1;
        num = 2L;
    }
}
