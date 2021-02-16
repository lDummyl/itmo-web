package it.itmo.first.dto;

import java.time.LocalDate;

public class Car {
    private Integer userId;
    private String brandName;
    private String brandModelName;
    private LocalDate productionDate;
    private String color;
    private CarType type;

    public Car() {
    }

    public Car(Integer id, String brandName) {
        this.userId = id;
        this.brandName = brandName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandModelName() {
        return brandModelName;
    }

    public void setBrandModelName(String brandModelName) {
        this.brandModelName = brandModelName;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + userId +
                ", brandName='" + brandName + '\'' +
                ", brandModelName='" + brandModelName + '\'' +
                ", productionDate=" + productionDate +
                ", color='" + color + '\'' +
                '}';
    }
}
