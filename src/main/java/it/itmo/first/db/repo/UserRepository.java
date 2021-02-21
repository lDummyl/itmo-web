package it.itmo.first.db.repo;

import it.itmo.first.db.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
       List<UserEntity> findAllByBirthdateAfter(LocalDate localDate);

//       @Query("select u from UserEntity u where u.birthdate >= :localDate")
//       List<UserEntity> findAllByQuery(LocalDate localDate);

       List<UserEntity> findAll();

       @Query("select u from UserEntity u where u.name = :name")
       List<UserEntity> findAllByQuery(String name);

       List<UserEntity> findUserEntityByName(String name);
}
