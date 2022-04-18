package com.auto.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 在 IOC容器还没有建立之前我们先去检测我们的资源是否都已经注册好了
 *
 */
@Component
class MyApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("ApplicationContextInitializer<========>initialize---001");
    }
}
