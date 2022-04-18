package com.mq.topicprovider;

import com.mq.topicprovider.service.SendService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext run = SpringApplication.run(Application.class, args);
		SendService sendService =(SendService) run.getBean("SendService");
		sendService.topicSendMessage("topic测试数据");
	}

}
