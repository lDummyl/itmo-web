package it.itmo.first.service;

import it.itmo.first.dto.Car;

import java.util.List;

public interface CarService {
        /**
         * Создает новую машину
         * @param car - клиент для создания
         */
        void create(Car car);

        /**
         * Возвращает список всех имеющихся машин
         * @return список машин
         */
        List<Car> readAll();

        /**
         * Возвращает машину по ее ID
         * @param id - ID машины
         * @return - объект машины с заданным ID
         */
        Car read(int id);

        /**
         * Обновляет машину с заданным ID,
         * в соответствии с переданной машиной
         * @param car - машина в соответсвии с которой нужно обновить данные
         * @param id - id клиента которого нужно обновить
         * @return - true если данные были обновлены, иначе false
         */
        boolean update(Car car, int id);

        /**
         * Удаляет машину с заданным ID
         * @param id - id машины, которую нужно удалить
         * @return - true если машина была удалена, иначе false
         */
        boolean delete(int id);
}
