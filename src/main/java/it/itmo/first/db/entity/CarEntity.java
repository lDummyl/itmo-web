package it.itmo.first.db.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "car")
public class CarEntity {
    @Id
    private Integer id;
    private String model;
    private String brend;
    private Integer year_of_release;

    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", brend='" + brend + '\'' +
                ", year_of_release=" + year_of_release +
                '}';
    }

    public CarEntity() {
    }

    public Integer getYearofrelease() {
        return year_of_release;
    }

    public void setYearofrelease(Integer yearofrelease) {
        this.year_of_release = year_of_release;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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



}

