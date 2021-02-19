package it.itmo.first.db.JpaRepository;

import it.itmo.first.db.entity.CarEntity;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<CarEntity,Integer> {  //PagingAndSortingRepository

}
