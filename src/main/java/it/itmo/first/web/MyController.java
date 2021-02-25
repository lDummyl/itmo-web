package it.itmo.first.web;


import it.itmo.first.dto.Gender;
import it.itmo.first.dto.Representation;
import it.itmo.first.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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


    private final UserService userService;
    public MyController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Добавить реализацию контроля за уникальностью,
     * если пользоваетль уже есть "nice to see you again." и запись не деалем.
     * */
    @GetMapping
    @RequestMapping("/hello")
    public String  sayHello(String name) {
        return userService.sayHello(name);
    }

    @PostMapping
    @RequestMapping("/returnWithId")
    public Representation sayHello(@RequestBody Representation representation) {
        Representation result = userService.save(representation);
        return result;
    }

    @GetMapping
    @RequestMapping("/listOfVisitors")
    public List<String> getVisitors() {
        return userService.names();
    }

    @GetMapping
    @RequestMapping("/goodbye")
    public String sayGoodbye() {
        return "Bye-bye Spring!";
    }

//-----------------------------------------------------------------------------------------------------------

    @GetMapping
    @RequestMapping("/users/add")
    public String addUser(Representation representation){
         return userService.addUser(representation);
    }
    @PutMapping("/users/{id}")
    public String edit(@PathVariable("id") Integer id, String name, String email, LocalDate birthdate, Gender gender){
       return userService.edit(id, name, email, birthdate, gender);
    }


    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") Integer id){
        return userService.delete(id);
    }

    @GetMapping
    @RequestMapping("/users/show")
    public String showUsers(){
        return userService.showUsers();
    }


}
