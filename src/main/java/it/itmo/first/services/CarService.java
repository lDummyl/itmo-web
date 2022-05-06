package it.itmo.first.services;


import it.itmo.first.db.entity.CarEntity;
import it.itmo.first.db.entity.UserEntity;
import it.itmo.first.db.repo.CarRepository;
import it.itmo.first.db.repo.UserRepository;
import it.itmo.first.dto.Car;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class CarService {
    private CarRepository carRepository;
    private UserRepository userRepository;


    public CarService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init(){

        List<CarEntity> allByQuery = carRepository.findAllByQuery(LocalDate.ofYearDay(2019, 1));
        for (CarEntity carEntity : allByQuery) {
            System.out.println(carEntity);
        }
    }

    public String create(Car car) {
        CarEntity carEntity = getCarEntity(car);

        Integer carId = carEntity.getId();

        List<CarEntity> all = carRepository.findAll();
        for (CarEntity entity : all) {
            if(!entity.getId().equals(carId) && carId != null) {
                carRepository.save(carEntity);
            }
        }
        return carEntity.toString() + " is created.";
    }
    public void addCar(String brandName, String brandModelName, String color) {
        Car car = new Car();
        car.setColor(color);
        car.setBrandName(brandName);
        car.setBrandModelName(brandModelName);
        CarEntity carEntity = getCarEntity(car);

        List<UserEntity> all = userRepository.findAll();
        UserEntity userEntity = all.get(all.size() - 1);
        carEntity.setOwner_id(userEntity.getId());
        carRepository.save(carEntity);

    }

    private CarEntity getCarEntity(Car car) {
        CarEntity carEntity = new CarEntity();
        carEntity.setBrandName(car.getBrandName());
        carEntity.setBrandModelName(car.getBrandModelName());
        carEntity.setProductionDate(car.getProductionDate());
        carEntity.setColor(car.getColor());
        carEntity.setType(car.getType());
        carEntity.setId(car.getId());
        return carEntity;
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


    public String showCars(String name) {
        String trimmed =  name.trim();

        List<UserEntity> users = userRepository.findAll();
        List<CarEntity> cars = carRepository.findAll();

        List<CarEntity> sortedById = new ArrayList<>();

        Integer ownerId = null;

        for(UserEntity userEntity : users){
            if(userEntity.getName().equals(trimmed)){
                ownerId = userEntity.getId();
            }
        }
        for(CarEntity carEntity : cars){
            if(carEntity.getOwner_id().equals(ownerId)){
                sortedById.add(carEntity);
            }
        }
        return sortedById.toString();
    }
}
