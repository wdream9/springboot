package com.thread.jue_FunctionInterface;

import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.function.Function;

public class FunctionInterfaceTest {

    public static void main(String[] args) {
        Function<String, String> stringStringFunction = new Function<>(){
            @Override
            public String apply(String s) {
                return null;
            }
        };
    }
}
