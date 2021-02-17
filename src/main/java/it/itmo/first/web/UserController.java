package it.itmo.first.web;

import it.itmo.first.dto.User;
import it.itmo.first.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    /*@RestController — говорит спрингу, что данный класс является REST контроллером.
    Т.е. в данном классе будет реализована логика обработки клиентских запросов.*/
@RequestMapping("/greetings")

public class UserController {
    private final IUserService userService;

    @Autowired
    /*@Autowired — говорит спрингу, что в этом месте необходимо внедрить зависимость. В конструктор мы передаем интерфейс
    CarService. Реализацию данного сервиса мы пометили аннотацией @Service ранее, и теперь спринг сможет передать экземпляр
    этой реализации в конструктор контроллера.*/

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/users")
    public ResponseEntity<?> create(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> read() {
        final List<User> users = userService.readAll();

        if (users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> read(@PathVariable int id) {
        final User user = userService.read(id);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody User user) {
        final boolean updated = userService.update(user, id);

        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        final boolean deleted = userService.delete(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
