package com.mq.fanoutconsumer;

import com.mq.fanoutconsumer.service.FanoutRecoverService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext run = SpringApplication.run(Application.class, args);
		FanoutRecoverService fanoutRecoverService =(FanoutRecoverService) run.getBean("FanoutRecoverService");
	}

}
