package it.itmo.first.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.itmo.first.db.JpaRepository.UserRepository;
import it.itmo.first.db.entity.UserEntity;
import it.itmo.first.dto.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service
/*Аннотация @Service говорит спрингу, что данный класс является сервисом. Это специальный тип классов,
в котором реализуется некоторая бизнес логика приложения. Впоследствии, благодаря этой аннотации Spring
будет предоставлять нам экземпляр данного класса в местах, где это, нужно с помощью Dependency Injection.*/
public class UserServiceDB implements IUserService{

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserServiceDB(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @PostConstruct
    public void init(){

    }

    @Override
    public void create(User user) {
        UserEntity userEntity = objectMapper.convertValue(user, UserEntity.class);
        userRepository.save(userEntity);
    }

    @Override
    public List<User> readAll() {
        Iterable<UserEntity> all = userRepository.findAll();
        return objectMapper.convertValue(all, List.class);
    }

    @Override
    public User read(int id) {
        if (userRepository.findById(id).isPresent()) {
            UserEntity userEntity = userRepository.findUserEntityByQuery(id).get(0);
            return objectMapper.convertValue(userEntity, User.class);
        }
        return null;
    }

    @Override
    public boolean update(User user, int id) {
        if (userRepository.findById(id).isPresent()){
            userRepository.save(objectMapper.convertValue(user, UserEntity.class));
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (userRepository.findById(id).isPresent()){
            UserEntity userEntity = userRepository.findById(id).stream().findFirst().get();
            userRepository.delete(userEntity);
            return true;
        }
        return false;
    }
}
