package it.itmo.first.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.itmo.first.db.entity.UserEntity;
import it.itmo.first.db.repo.UserRepository;
import it.itmo.first.dto.Gender;
import it.itmo.first.dto.Representation;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
//    private ObjectMapper objectMapper = new ObjectMapper();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init(){
//        UserEntity userEntity = new UserEntity();
//        userEntity.setName("Artur");
//        userEntity.setBirthdate(LocalDate.ofYearDay(1986, 120));

//        userRepository.save(userEntity);

//        List<UserEntity> allByBirthdateAfter = userRepository.findAllByQuery(LocalDate.ofYearDay(2000, 1));
//        for (UserEntity entity : allByBirthdateAfter) {
//            System.out.println(entity.toString());
//        }
    }

    public Representation save(Representation representation){
          UserEntity userEntity = new UserEntity();
          userEntity.setName(representation.getName());
          userEntity.setEmail(representation.getEmail());

          UserEntity save = userRepository.save(userEntity);

          Representation result = new Representation();
          result.setName(save.getName());
          result.setEmail(save.getEmail());
          return result;
    }

    public List<String> names(){
        List<String> names = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        for (UserEntity userEntity : userEntities) {
             names.add(userEntity.getName());
        }
        return names;
    }
    public String  sayHello(String name){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);

        return String.format("Hello, %s!", name);
    }

    public void addUserName(String name){
        Representation representation = new Representation();
        representation.setName(name);
        save(representation);
    }

    public String addUser(Representation representation){
        UserEntity user = new UserEntity();
        user.setId(representation.getId());

        for(UserEntity tempUser : userRepository.findAll()){
            if(tempUser.getId().equals(user.getId())){
                return "nice to see you again.";
            }
        }

        user.setName(representation.getName());
        user.setBirthdate(representation.getBirthdate());
        user.setGender(representation.getGender());
        user.setEmail(representation.getEmail());


        userRepository.save(user);

        return user.toString() + " is successfully added.";
    }

    public String edit( Integer id, String name, String email, LocalDate birthdate, Gender gender){
        for(UserEntity user : userRepository.findAll()){
            if(user.getId().equals(id)){
                user.setName(name);
                user.setEmail(email);
                user.setBirthdate(birthdate);
                user.setGender(gender);

                userRepository.save(user);
                return user.toString() + " is successfully edited.";
            }
        }
        return "User is not found!";
    }

    public String delete(Integer id){
        for(UserEntity user : userRepository.findAll()){
            if(user.getId().equals(id)){
                userRepository.delete(user);
                return user.toString() + " has been successfully removed.";
            }
        }
        return "User is not found!";
    }
    public String showUsers(){
        return userRepository.findAll().toString();
    }


}
