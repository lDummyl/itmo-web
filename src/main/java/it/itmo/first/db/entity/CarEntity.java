package it.itmo.first.db.entity;


import it.itmo.first.dto.CarType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(schema = "public", name = "car")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String brandName;
    private String brandModelName;
    private LocalDate productionDate;
    private String color;
    private CarType type;
    private Integer owner_id;


    public CarEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String model) {
        this.brandName = model;
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

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", brandModelName='" + brandModelName + '\'' +
                ", productionDate=" + productionDate +
                ", color='" + color + '\'' +
                ", type=" + type +
                ", owner_id=" + owner_id +
                '}';
    }
}
