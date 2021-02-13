package it.itmo.first.dto;

import java.time.LocalDate;

/*
 * Добавть новый контроллер для автомобилей, сделать класс Car
 * модель, марка, год выпуска и тд. Собственник int id пользователя по индуексу в листе.
 * Реализовать добваление удаление маштн и  изменение собственника все через web интерфейс.
 *
 *
 * */
public class Car {
    private String model;
    private String brend;
    private LocalDate yearOfRelease;
    private int Owner;

    public Car(String model, String brend, LocalDate yearOfRelease, int owner) {
        this.model = model;
        this.brend = brend;
        this.yearOfRelease = yearOfRelease;
        Owner = owner;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrend() {
        return brend;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }

    public LocalDate getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(LocalDate yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public int getOwner() {
        return Owner;
    }

    public void setOwner(int owner) {
        Owner = owner;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", brend='" + brend + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", Owner=" + Owner +
                '}';
    }
}


