package it.itmo.first.services;

import it.itmo.first.db.entity.UserEntity;
import it.itmo.first.db.repo.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Artur");
        userEntity.setBirthdate(LocalDate.ofYearDay(1986, 120));

//        userRepository.save(userEntity);

        List<UserEntity> allByBirthdateAfter = userRepository.findAllByQuery(LocalDate.ofYearDay(2000, 1));
        for (UserEntity entity : allByBirthdateAfter) {
            System.out.println(entity.toString());
        }
    }
}
