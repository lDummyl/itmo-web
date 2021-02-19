package it.itmo.first.web;

import it.itmo.first.dto.Car;
import it.itmo.first.dto.CarType;
import it.itmo.first.services.CarService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/greetings")
public class CarController {


   private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    @RequestMapping("/cars/add")
    public String create(Car car) {
        return carService.create(car);
    }

    @PutMapping
    @RequestMapping("/cars/{id}/edit")
    public String update(@PathVariable("id") Integer id, Car car) {

        return carService.update(id, car);
    }

    @DeleteMapping
    @RequestMapping("/cars/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {

        return carService.delete(id);
    }

    @GetMapping
    @RequestMapping("/cars/show")
    public String showUsers(){
        return carService.showUsers();
    }


}
