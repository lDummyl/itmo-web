package it.itmo.first.web;

import it.itmo.first.dto.Car;
import it.itmo.first.dto.CarCRUD;
import it.itmo.first.dto.CarType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/greetings")
public class CarController {


   private final CarCRUD carCRUD = new CarCRUD();

    @GetMapping
    @RequestMapping("/cars")
    public String create(Car car) {
        assert car != null;
        carCRUD.create(car);

        return car.toString() + " is created.";
    }
    @GetMapping
    @RequestMapping("/cars/{id}/edit")
    public String update(@PathVariable("id") Integer id, String brandName, String brandModelName,
                          LocalDate productionDate, String color, CarType type) {

       carCRUD.update(id, brandName, brandModelName, productionDate, color, type);

        return "Car id = " + id + " has been edited.";
    }

    @GetMapping
    @RequestMapping("/cars/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        assert id != null;
        carCRUD.delete(id);

        return "Car id = " + id + " has been deleted.";
    }

    @GetMapping
    @RequestMapping("/cars/show")
    public String showUsers(){
        Map<Integer, Car> carMap = carCRUD.carMap();

        return carMap.toString();
    }


}
