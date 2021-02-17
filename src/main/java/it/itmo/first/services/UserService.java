package it.itmo.first.services;

import it.itmo.first.db.entity.UserEntity;
import it.itmo.first.db.repo.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init(){
        Iterable<UserEntity> all = userRepository.findAll();
        for (UserEntity userEntity : all) {

            System.out.println(userEntity.toString());
        }
    }
}
