package it.itmo.first.db.entity;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "car")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// используем то значение, которое будет присваиваться в БД
    private Integer id;
    private String model;
    private String brend;
    private Integer year_of_release;
    private Integer owner_id;

    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", brend='" + brend + '\'' +
                ", year_of_release=" + year_of_release +
                ", owner_id=" + owner_id +
                '}';
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
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

    public Integer getYear_of_release() {
        return year_of_release;
    }

    public void setYear_of_release(Integer year_of_release) {
        this.year_of_release = year_of_release;
    }
}

