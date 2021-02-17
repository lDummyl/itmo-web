package it.itmo.first.web;


import it.itmo.first.dto.Gender;
import it.itmo.first.dto.Representation;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/*
 * Реализовать удаление и изменение данных пользователя,
 * добавить новые поля
 *
 * */

/**
 * Добавть новый контроллер для автомобилей, сделать класс Car
 * модель, марка, год выпуска и тд. Собственник int id пользователя по индуексу в листе.
 * Реализовать добваление удаление маштн и  изменение собственника все через web интерфейс.
 *
 * */

@RestController
@RequestMapping("/greetings")
public class MyController {

    private final List<String> names = new ArrayList<>();
    public final List<Representation> users = new ArrayList<>();
    {
        Representation user1 = new Representation(1, "John");
        user1.setGender(Gender.MALE);
        user1.setBirthdate(LocalDate.ofYearDay(1986, 150));
        user1.setEmail("john@mail.ru");
        Representation user2 = new Representation(2, "Alice");
        user1.setGender(Gender.FEMALE);
        user1.setBirthdate(LocalDate.ofYearDay(1996, 155));
        user1.setEmail("alice@mail.ru");

        users.add(user1);
        users.add(user2);
    }

    /**
     * Добавить реализацию контроля за уникальностью,
     * если пользоваетль уже есть "nice to see you again." и запись не деалем.
     * */
    @GetMapping
    @RequestMapping("/hello")
    public String  sayHello(String name) {
        names.add(name);
        return String.format("Hello, %s!", name);
    }

    @PostMapping
    @RequestMapping("/returnWithId")
    public Representation sayHello(@RequestBody Representation representation) {
        String name = representation.getName();
        names.add(name);
        representation.setId(names.indexOf(name));
        return representation;
    }

    @GetMapping
    @RequestMapping("/listOfVisitors")
    public List<String> getVisitors() {
        return names;
    }

    @GetMapping
    @RequestMapping("/goodbye")
    public String sayGoodbye() {
        return "Bye-bye Spring!";
    }

//-----------------------------------------------------------------------------------------------------------

    @GetMapping
    @RequestMapping("/users/add")
    public String addUser(Integer id, String name, String email, LocalDate birthdate, Gender gender){
        Representation user = new Representation(id, name);

        for(Representation tempUser : users){
            if(tempUser.getId().equals(user.getId())){
                return "nice to see you again.";
            }
        }
        user.setEmail(email);
        user.setBirthdate(birthdate);
        user.setGender(gender);

        users.add(user);
         return user.toString() + " is successfully added.";
    }
    @GetMapping
    @RequestMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") Integer id, String name, String email, LocalDate birthdate, Gender gender){
       for(Representation user : users){
           if(user.getId().equals(id)){
               user.setName(name);
               user.setEmail(email);
               user.setBirthdate(birthdate);
               user.setGender(gender);
               return user.toString() + " is successfully edited.";
           }
       }
       return "User is not found!";
    }

    @GetMapping
    @RequestMapping("/users/{id}/delete")
    public String delete(@PathVariable("id") Integer id){
        for(Representation user : users){
            if(user.getId().equals(id)){
                users.remove(user);
                return user.toString() + " has been successfully removed.";
            }
        }
        return "User is not found!";
    }


    @GetMapping
    @RequestMapping("/users/show")
    public String showUsers(){
        return users.toString();
    }
}
