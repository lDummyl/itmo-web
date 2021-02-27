package it.itmo.first.config;

import it.itmo.first.beans.UserFramesBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:userFrames.properties")
public class UserFramesConfig {

    @Value("${minAge}")
    Integer minAge;

    @Value("${maxCars}")
    Integer maxCars;

    @Bean
    public UserFramesBean userFramesBean(){
        UserFramesBean userFramesBean = new UserFramesBean();
        return userFramesBean;
    }


    public UserFramesConfig() {
        System.out.println("Phase 1 UserFramesConfig constructor");
    }

    @PostConstruct
    public void init(){
        System.out.println("Phase 2 UserFramesConfig PostConstructor");
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxCars() {
        return maxCars;
    }

    public void setMaxCars(Integer maxCars) {
        this.maxCars = maxCars;
    }
}
