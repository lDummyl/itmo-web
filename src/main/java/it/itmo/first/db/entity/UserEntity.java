package it.itmo.first.db.entity;

import it.itmo.first.dto.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(schema = "public", name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private LocalDate birthdate;
    private User.Gender gender;

    @OneToMany(fetch = FetchType.EAGER)// все вытаскиваем
//    @OneToMany(fetch = FetchType.LAZY)// не все вытаскиваем
    @JoinColumn(name = "owner_id")//подтягиваем колонку owner_id из таблицы car и джойним id юзера
    private List<CarEntity> carEntities;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public User.Gender getGender(User.Gender male) {
        return gender;
    }

    public void setGender(User.Gender gender) {
        this.gender = gender;
    }

    public List<CarEntity> getCarEntities() {
        return carEntities;
    }

    public void setCarEntities(List<CarEntity> carEntities) {
        this.carEntities = carEntities;
    }

    public UserEntity() {
    }


    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carEntities=" + carEntities +
                '}';
    }
}
