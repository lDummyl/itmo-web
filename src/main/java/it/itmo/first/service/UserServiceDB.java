package it.itmo.first.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.itmo.first.config.UserFramesConfig;
import it.itmo.first.db.JpaRepository.UserRepository;
import it.itmo.first.db.entity.UserEntity;
import it.itmo.first.dto.User;
import it.itmo.first.exception.MyException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
/*Аннотация @Service говорит спрингу, что данный класс является сервисом. Это специальный тип классов,
в котором реализуется некоторая бизнес логика приложения. Впоследствии, благодаря этой аннотации Spring
будет предоставлять нам экземпляр данного класса в местах, где это, нужно с помощью Dependency Injection.*/
public class UserServiceDB implements IUserService{

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final UserFramesConfig userFramesConfig;

    public UserServiceDB(UserRepository userRepository, ObjectMapper objectMapper, UserFramesConfig userFramesConfig) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.userFramesConfig = userFramesConfig;
    }

    @Transactional
    @PostConstruct
    public void init(){

    }

    @Override
    public void create(User user) {
        UserEntity userEntity = objectMapper.convertValue(user, UserEntity.class);
        //TODO здесь сделать проверку на возраст и на количество машин
        System.out.println(userFramesConfig.getMaxCars());

//        userRepository.save(userEntity);
        if (!userRepository.existsById(userRepository.save(userEntity).getId())) {
            throw new MyException("user don't save in DB");
        }
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
