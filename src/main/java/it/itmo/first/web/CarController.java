package it.itmo.first.web;

import it.itmo.first.dto.Car;
import it.itmo.first.dto.CarType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/greetings")
public class CarController {


   private final Map<Integer,Car> cars = new HashMap<>();
    {
        Car car1 = new Car(1, "BMW");
        car1.setBrandModelName("525");
        car1.setProductionDate(LocalDate.ofYearDay(2019, 100));
        car1.setColor("Black");
        car1.setType(CarType.SEDAN);

        Car car2 = new Car(2, "Mercedes");
        car2.setBrandModelName("E300");
        car2.setProductionDate(LocalDate.ofYearDay(2020, 15));
        car2.setColor("White");
        car2.setType(CarType.CONVERTIBLE);

        cars.put(1,car1);
        cars.put(2,car2);

    }

    @PostMapping
    @RequestMapping("/cars/add")
    public String create(Car car) {
        Integer carId = car.getId();
        if(!cars.containsKey(carId) && carId != null) {
            cars.put(carId, car);
        }

        return car.toString() + " is created.";
    }
    @PutMapping
    @RequestMapping("/cars/{id}")
    public String update(@PathVariable("id") Integer id, Car car) {
       if(cars.containsKey(id)){
           cars.put(id, car);
       }
        return "Car id = " + id + " has been edited.";
    }

//    @DeleteMapping
//    @RequestMapping("/cars/{id}")
//    public String delete(@PathVariable("id") Integer id) {
//        cars.remove(id);
//        return "Car id = " + id + " has been deleted.";
//    }

    @GetMapping
    @RequestMapping("/cars/show")
    public String showUsers(){
        return cars.toString();
    }


}
