package it.itmo.first.web;

import it.itmo.first.dto.Car;
import it.itmo.first.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    /*@RestController — говорит спрингу, что данный класс является REST контроллером.
    Т.е. в данном классе будет реализована логика обработки клиентских запросов.*/
@RequestMapping("/greetings")

public class CarController {
    private final ICarService carService;

    @Autowired
    /*@Autowired — говорит спрингу, что в этом месте необходимо внедрить зависимость. В конструктор мы передаем интерфейс
    CarService. Реализацию данного сервиса мы пометили аннотацией @Service ранее, и теперь спринг сможет передать экземпляр
    этой реализации в конструктор контроллера.*/

    public CarController(ICarService carService) {
        this.carService = carService;
    }


    @PostMapping(value = "/cars")
    public ResponseEntity<?> create(@RequestBody Car car) {
            carService.create(car);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/cars")
    public ResponseEntity<List<Car>> read() {
        final List<Car> cars = carService.readAll();

        if (cars != null && !cars.isEmpty()) {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cars/{id}")
    public ResponseEntity<Car> read(@PathVariable int id) {
        final Car car = carService.read(id);

        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/cars/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Car car) {
        final boolean updated = carService.update(car, id);

        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/cars/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        final boolean deleted = carService.delete(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
