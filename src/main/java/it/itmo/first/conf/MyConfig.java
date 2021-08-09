package it.itmo.first.conf;


import it.itmo.first.beans.*;
import it.itmo.first.serv.Serv;
import it.itmo.first.web.MyController;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;

@Configuration
@PropertySource("classpath:some.properties")
public class MyConfig {

    @Value("${name}")
    String name;

    @Value("${prop}")
    String p;

    @Value("${prop-main}")
    String p0;

    @Autowired
    ApplicationContext context;

    @Bean
    public Serv MyServiceAlias(ApplicationContext context, @Value("${bean}") String beanName, Serv serv) {
        Face bean = (Face) context.getBean(beanName);
        serv.setFace(bean);
        return serv;
    }


    @Bean
    public SomeBean someBean(@Qualifier("child") SomeParentBean someParentBean) {
        SomeBean someBean = new SomeBean();
        someBean.setName(someParentBean.getName());
        return someBean;
    }

    @Bean
    public SomeBean someOtherBean() {
        SomeBean someBean = new SomeBean();
        someBean.setName("Name");
        return someBean;
    }

    public MyConfig() {
        System.out.println("Phase 1");
    }

    @PostConstruct
    private void init() {
//        privateKey =
        System.out.println("Phase 2");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
