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
    public List<Representation> getUsers(){
        return users;
    }

    @GetMapping
    @RequestMapping("/addUser")
    public String addUser(String id, String name, String email, String birthdate, String gender) {
        try {
            Integer idInteger = Integer.parseInt(id);

            for (Representation representation : users) {
                if (representation.getId().equals(idInteger)) {
                    return "nice to see you again.";
                }
            }

            Representation user = new Representation(idInteger, name, email, LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd-MM-yyyy")), Representation.Gender.valueOf(gender));
            users.add(user);

            return "Пользователь " + user.toString() + " добавлен в список пользователей";
        } catch (Exception e) {
            return "Вводимые данные некорректны";
        }
    }

    @GetMapping
    @RequestMapping("/deleteUser")
    public String deleteUser(String id) {
        try {
            Integer idInteger = Integer.parseInt(id);
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId().intValue() == idInteger.intValue()) {
                    users.remove(i);
                    return "Пользователь c id = " + idInteger + " удален из списка пользователей";
                }
            }
            return "Пользователя с id = " + idInteger + " нет в списке пользователей";
        } catch (Exception e) {
            return "Вводимые данные некорректны";
        }
    }

    @GetMapping
    @RequestMapping("/changeUser")
    public String changeUser(String id, String name, String email, String birthdate, String gender) {
        try {
            if (id==null) return "Не указан id изменяемого пользователя";
            Integer idInteger = Integer.parseInt(id);
            for (int i = 0, usersSize = users.size(); i < usersSize; i++) {
                Representation user = users.get(i);
                if (user.getId().equals(idInteger)) {
                    // создадим клон изменяемого объекта для единого внесения всех изменений
                    Representation userClone = new Representation(user.getId(), user.getName(), user.getEmail(), user.getBirthdate(), user.getGender());

                    //попытаемся заменить поля клона пришедшими в строке-запросе элементами
                    if (name != null) {
                        userClone.setName(name);
                    }
                    if (email != null) {
                        userClone.setEmail(email);
                    }
                    if (birthdate != null) {
                        userClone.setBirthdate(LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    }
                    if (gender != null) {
                        userClone.setGender(Representation.Gender.valueOf(gender));
                    }

                    //если исключений не было заменяем оригинал на клон (Что происходит со старым объектом?)
                    users.set(i, userClone);
                    return "Пользователь c id = " + idInteger + " изменен";
                }
            }
            return "Пользователя с id = " + idInteger + " нет в списке пользователей";

        } catch (Exception e) {
            return "Вводимые данные некорректны";
        }
    }

    @GetMapping
    @RequestMapping("/showUsers")
    public List<Representation> showUsers() {
        return users;
    }

}
