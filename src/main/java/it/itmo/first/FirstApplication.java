package it.itmo.first;

import it.itmo.first.beans.SomeBean;
import it.itmo.first.beans.SomeParentBean;
import it.itmo.first.conf.MyConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FirstApplication {


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(FirstApplication.class, args);
		SomeBean myConfig =  context.getBean("someBean", SomeBean.class);
		myConfig.setName("Clutch");

		System.out.println("myConfig = " + myConfig);


	}

}
