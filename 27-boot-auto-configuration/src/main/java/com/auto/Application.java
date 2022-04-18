package com.auto;

import com.auto.config.Person;
import com.auto.importselect.Black;
import com.auto.importselect.EnableAutoImport;
import com.auto.importselect.White;
import org.springframework.beans.factory.parsing.BeanEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableAutoImport
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
		Black bean = run.getBean(Black.class);
		System.out.println(bean);
	}

}
