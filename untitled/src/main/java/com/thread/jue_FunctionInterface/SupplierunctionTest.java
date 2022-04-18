package com.thread.jue_FunctionInterface;

import javax.sound.midi.Soundbank;
import java.util.function.Supplier;

public class SupplierunctionTest {
    public static void main(String[] args) {
        Supplier supplier = new Supplier(){
            @Override
            public Integer get() {
                return 1024;
            }
        };

        System.out.println(supplier.get());

        // lambda表达式简写
        Supplier<Integer> supplier1 = ()->{
            return 1000;
        };
        System.out.println("lambda表达式简写"+supplier1.get());
    }
}
