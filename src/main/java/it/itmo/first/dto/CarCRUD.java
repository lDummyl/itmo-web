package it.itmo.first.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CarCRUD {
    private final Map<Integer, Car> carMap = new HashMap<>();

    public void create(Car car){
        if(car != null && !carMap.containsKey(car.getUserId())) {
            carMap.put(car.getUserId(), car);
        }
    }

    public void update(Integer id, String brandName, String brandModelName,
                       LocalDate productionDate, String color, CarType type){
        Car car = null;
        if(carMap.containsKey(id)){
           car = carMap.get(id);
           car.setBrandName(brandName);
           car.setBrandModelName(brandModelName);
           car.setProductionDate(productionDate);
           car.setColor(color);
           car.setType(type);
        }
    }

    public void delete(Integer id){
        if(id != null) {
            carMap.remove(id);
        }
    }
}
