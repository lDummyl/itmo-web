package it.itmo.first.services;


import it.itmo.first.db.entity.CarEntity;
import it.itmo.first.db.repo.CarRepository;
import it.itmo.first.dto.Car;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;


@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @PostConstruct
    public void init(){
//        CarEntity carEntity = new CarEntity();
//        carEntity.setId(2);
//        carEntity.setOwner_id(140);
//        carEntity.setBrandName("BMW");
//        carEntity.setBrandModelName("525");
//        carEntity.setColor("Black");
//        carEntity.setType(CarType.SEDAN);
//        carEntity.setProductionDate(LocalDate.of(2020, 5, 9));

//
//        carRepository.save(carEntity);

        List<CarEntity> allByQuery = carRepository.findAllByQuery(LocalDate.ofYearDay(2019, 1));
        for (CarEntity carEntity : allByQuery) {
            System.out.println(carEntity);
        }
    }

    public String create(Car car) {
        CarEntity carEntity = new CarEntity();
        carEntity.setBrandName(car.getBrandName());
        carEntity.setBrandModelName(car.getBrandModelName());
        carEntity.setProductionDate(car.getProductionDate());
        carEntity.setColor(car.getColor());
        carEntity.setType(car.getType());
        carEntity.setId(car.getId());

        Integer carId = carEntity.getId();

        List<CarEntity> all = carRepository.findAll();
        for (CarEntity entity : all) {
            if(!entity.getId().equals(carId) && carId != null) {
                carRepository.save(carEntity);
            }
        }
        return carEntity.toString() + " is created.";
    }

    public String update(Integer id, Car car) {
        List<CarEntity> all = carRepository.findAll();
        for (CarEntity carEntity : all) {
            if(carEntity.getId().equals(id)){
                carEntity.setBrandName(car.getBrandName());
                carEntity.setBrandModelName(car.getBrandModelName());
                carEntity.setProductionDate(car.getProductionDate());
                carEntity.setColor(car.getColor());
                carEntity.setType(car.getType());
                carEntity.setId(car.getId());

                carRepository.save(carEntity);
                return "Car " + car.getBrandName() + " id = " + id + " has been edited.";
            }
        }
        return "Car " + car.getBrandName() + " id = " + id + " hasn't been edited.";
    }


    public String delete(Integer id) {
        List<CarEntity> all = carRepository.findAll();
        for (CarEntity carEntity : all) {
            if (carEntity.getId().equals(id)){
                carRepository.delete(carEntity);
                return "Car id = " + id + " has been deleted.";
            }
        }
        return "Car id = " + id + " hasn't been deleted.";
    }

    public String showUsers(){
        return carRepository.findAll().toString();
    }
}
