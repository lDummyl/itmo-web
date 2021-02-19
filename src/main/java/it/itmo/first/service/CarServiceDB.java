package it.itmo.first.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.itmo.first.db.JpaRepository.CarRepository;
import it.itmo.first.db.entity.CarEntity;
import it.itmo.first.dto.Car;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
/*Аннотация @Service говорит спрингу, что данный класс является сервисом. Это специальный тип классов,
в котором реализуется некоторая бизнес логика приложения. Впоследствии, благодаря этой аннотации Spring
будет предоставлять нам экземпляр данного класса в местах, где это, нужно с помощью Dependency Injection.*/
public class CarServiceDB implements ICarService{

    private final CarRepository carRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CarServiceDB(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional
    @PostConstruct
    public void init(){

    }

    @Override
    public void create(Car car) {
        CarEntity carEntity = objectMapper.convertValue(car, CarEntity.class);
        carRepository.save(carEntity);
    }

    @Override
    public List<Car> readAll() {
        Iterable<CarEntity> all = carRepository.findAll();
        return objectMapper.convertValue(all, ArrayList.class);
    }

    @Override
    public Car read(int id) {
        if (carRepository.findById(id).isPresent()) {
            CarEntity carEntity = carRepository.findById(id).stream().findFirst().get();
            return objectMapper.convertValue(carEntity, Car.class);
        }
        return null;
    }

    @Override
    public boolean update(Car car, int id) {
        if (carRepository.findById(id).isPresent()){
            CarEntity carEntity = carRepository.findById(id).stream().findFirst().get();
            carRepository.save(carEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (carRepository.findById(id).isPresent()){
            CarEntity carEntity = carRepository.findById(id).stream().findFirst().get();
            carRepository.delete(carEntity);
            return true;
        }
        return false;
    }
}