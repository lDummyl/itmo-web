package it.itmo.first.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import it.itmo.first.beans.Face;
import it.itmo.first.dto.Representation;
import it.itmo.first.exception.MyException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


/**
 * Реализовать удаление и изменение данных пользователя,
 * добавить новые поля
 */

/**
 * Добавть новый контроллер для автомобилей, сделать класс Car
 * модель, марка, год выпуска и тд. Собственник int id пользователя по индуексу в листе.
 * Реализовать добваление удаление маштн и  изменение собственника все через web интерфейс.
 */

@Slf4j
@RestController
@RequestMapping("/greetings")
public class MyController {


    ObjectMapper objectMapper;


    @PostConstruct
    public void init(){
        System.out.println();
    }

    private List<String> names = new ArrayList<>();

    /**
     * Добавить реализацию контроля за уникальностью,
     * если пользоваетль уже есть "nice to see you again." и запись не деалем.
     */
    @GetMapping
    @RequestMapping("/hello")
    public String sayHello(String name) {
        if (true) throw new MyException("my message");
        names.add(name);
        return String.format("Hello, %s!", name);
    }

    @SneakyThrows
    @PostMapping
    @RequestMapping("/returnWithId")
    public Representation sayHello(@RequestBody Representation representation) {
        log.debug(objectMapper.writeValueAsString(representation));

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

}
