package it.itmo.first.web;

import it.itmo.first.dto.Car;
import it.itmo.first.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greetings")

public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    /*@RestController — говорит спрингу, что данный класс является REST контроллером. Т.е. в данном классе будет реализована логика обработки клиентских запросов
    @Autowired — говорит спрингу, что в этом месте необходимо внедрить зависимость. В конструктор мы передаем интерфейс
    CarService. Реализацию данного сервиса мы пометили аннотацией @Service ранее, и теперь спринг сможет передать экземпляр
    этой реализации в конструктор контроллера.*/

    @PostMapping(value = "/cars")
    public ResponseEntity<?> create(@RequestBody Car car) {
        try {
            carService.create(car);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (RuntimeException re){
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping(value = "/cars")
    public ResponseEntity<List<Car>> read() {
        final List<Car> cars = carService.readAll();

        return cars != null &&  !cars.isEmpty()
                ? new ResponseEntity<>(cars, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cars/{id}")
    public ResponseEntity<Car> read(@PathVariable(name = "id") int id) {
        final Car car = carService.read(id);

        return car != null
                ? new ResponseEntity<>(car, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/cars/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Car car) {
        final boolean updated = carService.update(car, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/cars/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = carService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


}
