package it.itmo.first.web;


import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import it.itmo.first.dto.Representation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Реализовать удаление и изменение данных пользователя,
 * добавить новые поля
 * [добавил для первого коммита]
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

    private List<String> names = new ArrayList<>();

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

    private List<Representation> users = new ArrayList<>();
    @GetMapping
    @RequestMapping("/addUser")
    public String addNewUser(Integer id, String name, String email, String birthdate, String gender) {
        Representation user = new Representation(id, name);
        user.setEmail(email);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d',' yyyy hh':'mm a", Locale.US);
        LocalDate b =LocalDate.parse(birthdate , DateTimeFormatter.ofPattern ( "dd-MM-yyyy"));
        user.setBirthdate(b);
        user.setGender(Representation.Gender.valueOf(gender));
        return "Пользователь " + user.toString() + " добавлен в список пользователей";
    }



}
