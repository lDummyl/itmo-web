package it.itmo.first.service;

import it.itmo.first.dto.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
/*Аннотация @Service говорит спрингу, что данный класс является сервисом. Это специальный тип классов,
в котором реализуется некоторая бизнес логика приложения. Впоследствии, благодаря этой аннотации Spring
будет предоставлять нам экземпляр данного класса в местах, где это, нужно с помощью Dependency Injection.*/

public class CarServiceImpl implements CarService{

    // Хранилище машин
    private static final Map<Integer, Car> CAR_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID машины
    private static final AtomicInteger CAR_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Car car) {
        final int clientId = CAR_ID_HOLDER.incrementAndGet();
        car.setId(clientId);
        CAR_REPOSITORY_MAP.put(clientId, car);
    }

    @Override
    public List<Car> readAll() {
        return new ArrayList<>(CAR_REPOSITORY_MAP.values());
    }

    @Override
    public Car read(int id) {
        return CAR_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Car car, int id) {
        if (CAR_REPOSITORY_MAP.containsKey(id)) {
            car.setId(id);
            CAR_REPOSITORY_MAP.put(id, car);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return CAR_REPOSITORY_MAP.remove(id) != null;
    }
}