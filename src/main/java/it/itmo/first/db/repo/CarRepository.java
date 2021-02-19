package it.itmo.first.db.repo;


import it.itmo.first.db.entity.CarEntity;
import it.itmo.first.db.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends PagingAndSortingRepository<CarEntity, Integer> {

    @Query("select c from CarEntity c where c.productionDate >= :localDate")
    List<CarEntity> findAllByQuery(LocalDate localDate);
    List<CarEntity> findAllByProductionDate(LocalDate localDate);
    List<CarEntity> findAll();
}
