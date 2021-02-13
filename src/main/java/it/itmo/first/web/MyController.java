package it.itmo.first.web;

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


/*
 * Реализовать удаление и изменение данных пользователя,
 * добавить новые поля
 * [добавил для первого коммита]
 * */

/*
 * Добавть новый контроллер для автомобилей, сделать класс Car
 * модель, марка, год выпуска и тд. Собственник int id пользователя по индуексу в листе.
 * Реализовать добваление удаление маштн и  изменение собственника все через web интерфейс.
 *
 *
 * */

@RestController
@RequestMapping("/greetings")
public class MyController {

    private final List<String> names = new ArrayList<>();

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

    private final List<Representation> users = new ArrayList<>();

    @GetMapping
    @RequestMapping("/addUser")
    public String addNewUser(Integer id, String name, String email, String birthdate, String gender) {
        for (Representation representation : users) {
            if (representation.getId().intValue() == id.intValue()) {
                return "nice to see you again.";
            }
        }
        Representation user = new Representation(id, name);
        user.setEmail(email);
        LocalDate b =LocalDate.parse(birthdate , DateTimeFormatter.ofPattern ( "dd-MM-yyyy"));
        user.setBirthdate(b);
        user.setGender(Representation.Gender.valueOf(gender));
        users.add(user);
        return "Пользователь " + user.toString() + " добавлен в список пользователей";
    }

    @GetMapping
    @RequestMapping("/deleteUser")
    public String deleteUser(Integer id) {
        for (int i=0;i< users.size();i++){
            if (users.get(i).getId().intValue() == id.intValue()){
                users.remove(i);
                return "Пользователь c id = " + id + " удален из списка пользователей";
            }
        }
        return "Пользователя с id = " + id + " нет в списке пользователей";
    }

    @GetMapping
    @RequestMapping("/changeUser")
    public String changeUser(Integer id, String name, String email, String birthdate, String gender) {
        for (Representation user : users) {
            if (user.getId().intValue() == id.intValue()) {
                if (name!=null) {user.setName(name);}
                if (email!=null) {user.setEmail(email);}
                if (birthdate!=null) {user.setBirthdate(LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd-MM-yyyy")));}
                if (gender!=null) {user.setGender(Representation.Gender.valueOf(gender));}
                return "Пользователь c id = " + id + " изменен";
            }
        }
        return "Пользователя с id = " + id + " нет в списке пользователей";
    }



}
