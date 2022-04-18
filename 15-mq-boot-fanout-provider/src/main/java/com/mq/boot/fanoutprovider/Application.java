package com.mq.boot.fanoutprovider;

import com.mq.boot.fanoutprovider.service.SendService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext run = SpringApplication.run(Application.class, args);
		SendService sendServiceImpl =(SendService) run.getBean("SendService");
		sendServiceImpl.FanoutSendMessage("消息集成springboot测试消息-----fanout交换机进行测试");
	}

}
