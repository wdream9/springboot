package com.thread.jue_FunctionInterface;

import java.awt.*;
import java.util.function.Predicate;

public class PredirectTest {
    public static void main(String[] args) {
        // 判断是否为null
        Predicate<String> stringPredicate = new Predicate<>(){
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };

        System.out.println(stringPredicate.test("sd"));

        // 使用lambda表达式简写该写法
        Predicate<String> tt = (str)->{return str.isEmpty();};
        System.out.println(tt.test("sdf"));
    }
}
