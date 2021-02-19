package it.itmo.first.service;

import it.itmo.first.db.entity.UserEntity;
import it.itmo.first.db.JpaRepository.CarRepository;
import it.itmo.first.dto.User;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

//@Service
/*Аннотация @Service говорит спрингу, что данный класс является сервисом. Это специальный тип классов,
в котором реализуется некоторая бизнес логика приложения. Впоследствии, благодаря этой аннотации Spring
будет предоставлять нам экземпляр данного класса в местах, где это, нужно с помощью Dependency Injection.*/
public class UserService implements IUserService{
    // Хранилище пользователей
    private static final Map<Integer, User> USER_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID пользователя
    private static final AtomicInteger USER_ID_HOLDER = new AtomicInteger();

    private final CarRepository userRepository;

    public UserService(CarRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @PostConstruct
    public void init(){
        //вывод всех юзеров
//        Iterable<UserEntity> all = userRepository.findAll();
//        System.out.println(all.toString());

        //запись в БД
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(0);
//        userEntity.setName("Ivan");
//        userEntity.setBirthdate(LocalDate.of(1987,3,6));
//        userEntity.setEmail("maksimovav@yandex.ru");
//        userEntity.setGender(User.Gender.male);
//
//       userRepository.save(userEntity);


//        System.out.println(userRepository.findAllByBirthdateAfter(LocalDate.ofYearDay(1989, 1)));
//
//        System.out.println(userRepository.findAllByQuery(LocalDate.of(1989, 2, 8)));
    }

    @Override
    public void create(User user) {
        final int userId = USER_ID_HOLDER.incrementAndGet();
        user.setId(userId);
        USER_REPOSITORY_MAP.put(userId, user);

        //Работаем с БД
//        UserEntity userEntity = new UserEntity();
//
//        userRepository.save()
    }

    @Override
    public List<User> readAll() {

        return new ArrayList<>(USER_REPOSITORY_MAP.values());
    }

    @Override
    public User read(int id) {
        return USER_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(User user, int id) {
        if (USER_REPOSITORY_MAP.containsKey(id)) {
            user.setId(id);
            USER_REPOSITORY_MAP.put(id, user);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return USER_REPOSITORY_MAP.remove(id) != null;
    }
}
