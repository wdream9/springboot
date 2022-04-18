package com.mq.boot.mqdirectconsumer;

import com.mq.boot.mqdirectconsumer.service.DirectRecoverService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext run = SpringApplication.run(Application.class, args);
		DirectRecoverService directRecoverService =(DirectRecoverService) run.getBean("DirectRecoverService");
		//directRecoverService.receive(); // 不能持续的监听消息

	}

}
