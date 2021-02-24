package it.itmo.first.conf;


import it.itmo.first.beans.SomeBean;
import it.itmo.first.beans.SomeParentBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:some.properties")
public class MyConfig {

    @Value("${name}")
    String name;


    @Bean
    public SomeBean someBean(@Qualifier("child") SomeParentBean someParentBean){
        SomeBean someBean = new SomeBean();
        someBean.setName(someParentBean.getName());
        return someBean;
    }


    public MyConfig() {
        System.out.println("Phase 1");
    }

    @PostConstruct
    public void init(){
        System.out.println("Phase 2");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
