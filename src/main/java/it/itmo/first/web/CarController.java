package it.itmo.first.web;

import it.itmo.first.dto.Car;
import it.itmo.first.services.CarService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greetings")
public class CarController {


    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    @RequestMapping("/cars/add")
    public String create(Car car) {
        return carService.create(car);
    }


    @PutMapping("/cars/{id}")
    public String update(@PathVariable("id") Integer id, Car car) {

        return carService.update(id, car);
    }

    @DeleteMapping("/cars/{id}")
    public String delete(@PathVariable("id") Integer id) {

        return carService.delete(id);
    }

    @GetMapping
    @RequestMapping("/cars/show")
    public String showUsers(){
        return carService.showUsers();
    }


}
