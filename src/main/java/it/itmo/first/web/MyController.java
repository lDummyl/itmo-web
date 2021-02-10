package it.itmo.first.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import it.itmo.first.dto.Representation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/greetings")
public class MyController {

    private List<Representation> representationList = new ArrayList<>();

    @GetMapping
    @RequestMapping("/hello/{id}")
    public Representation sayHello(String name, @PathVariable Integer id) {
        return new Representation(id, name);
    }

    @PostMapping
    @RequestMapping("/hello")
    public Representation sayHello(@RequestBody Representation representation) {
        representationList.add(representation);
        representation.setId(999);
        return representation;
    }


    @GetMapping
    @RequestMapping("/goodbye")
    public String sayGoodbye() {
        return "Bye-bye Spring!";
    }

}
