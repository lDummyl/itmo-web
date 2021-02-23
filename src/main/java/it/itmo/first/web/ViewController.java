package it.itmo.first.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.itmo.first.dto.Car;
import it.itmo.first.dto.User;
import it.itmo.first.service.ICarService;
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
    private final ICarService carService;

    @Autowired
    /*@Autowired — говорит спрингу, что в этом месте необходимо внедрить зависимость. В конструктор мы передаем интерфейс
    UserService и UserService. Реализацию данного сервиса мы пометили аннотацией @Service ранее, и теперь спринг сможет передать экземпляр
    этой реализации в конструктор контроллера.*/
    public ViewController(IUserService userService, ICarService carService) {
        this.userService = userService;
        this.carService = carService;
    }


    @GetMapping()
    public String index(Model model) {
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
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/cars",headers = "Accept=application/json")
    public ResponseEntity<?> createCar(@RequestBody Car car) { //избавился от ошибки There was an unexpected error (type=Unsupported Media Type, status=415). Content type 'application/x-www-form-urlencoded;charset=UTF-8' not supported org.springframework.web.HttpMediaTypeNotSupportedException: Content type 'application/x-www-form-urlencoded;charset=UTF-8' not supported
        carService.create(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*@RequestMapping(value = "/cars", method = RequestMethod.GET)  //это пример редиректа
    public void method(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "/");
        httpServletResponse.setStatus(302);
    }*/

    @DeleteMapping(value = "/cars/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable int id) {
        final boolean deleted = carService.delete(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        final boolean deleted = userService.delete(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
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