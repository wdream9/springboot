package com.auto.listener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/**
 * 当应用启动完成后就会执行的方法
 *  比如，在应用启动好后，将数据库中的数据加载到我们的缓存之中
 */
@Component
public class MyApplicationRunner implements  ApplicationRunner  {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("MyApplicationRunner");
    }
}
