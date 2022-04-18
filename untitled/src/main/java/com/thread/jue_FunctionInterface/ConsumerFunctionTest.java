package com.thread.jue_FunctionInterface;

import java.util.function.Consumer;

public class ConsumerFunctionTest {
    public static void main(String[] args) {
        Consumer<String> con = new Consumer<>(){
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        // lambda表达式
        Consumer<String> consumer = (str)->{
            System.out.println(str);
        };
        consumer.accept("adfasdf");
    }
}
