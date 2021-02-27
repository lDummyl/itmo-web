package it.itmo.first;

import it.itmo.first.beans.SomeBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FirstApplication {

	public static void main(String[] args) {
//		SpringApplication.run(FirstApplication.class, args);

		// вот так можно дернуть бины из спринга:
		ConfigurableApplicationContext context = SpringApplication.run(FirstApplication.class, args);

		SomeBean myConfig =  context.getBean("someBean", SomeBean.class);
		System.out.println("myConfig = " + myConfig);
	}

}
