package it.itmo.first.db.repo;


import it.itmo.first.db.entity.CarEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarRepository extends PagingAndSortingRepository<CarEntity, Integer> {
}
