package it.itmo.first.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.itmo.first.dto.User;
import it.itmo.first.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@Controller отражает концепцию MVC

public class ViewController {
    private final IUserService userService;

    @Autowired
    /*@Autowired — говорит спрингу, что в этом месте необходимо внедрить зависимость. В конструктор мы передаем интерфейс
    CarService. Реализацию данного сервиса мы пометили аннотацией @Service ранее, и теперь спринг сможет передать экземпляр
    этой реализации в конструктор контроллера.*/

    public ViewController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String hello1(Model model) {
        ObjectMapper objectMapper = new ObjectMapper();
       List userList = userService.readAll();
        String string = null;

        try {
            string = objectMapper.writeValueAsString(userList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        model.addAttribute("userList",string);
        return "index";
    }

    @PostMapping(value = "/",headers = "Accept=application/json")
    public ResponseEntity<?> create(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping({"/hello/{name}"})
    public String hello(Model model, @PathVariable String name) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping(value = "/translate", method = RequestMethod.POST)
    public String translate(@RequestParam String textForm, Model model) {
        model.addAttribute("textForm", textForm);
        return "success";
    }

}