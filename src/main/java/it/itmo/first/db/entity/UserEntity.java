package it.itmo.first.db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import it.itmo.first.dto.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(schema = "public", name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //используем то значение, которое будет присваиваться в БД
    private Integer id;
    private String name;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthdate;
    private User.Gender gender;
//    @JsonSerialize(using = IterableSerializer.class)
//    @OneToMany(fetch = FetchType.EAGER)// все вытаскиваем
//    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)// не все вытаскиваем
    @JoinColumn(name = "owner_id")//подтягиваем колонку owner_id из таблицы car и джойним id юзера
//    @JsonIgnore
    private List<CarEntity> carEntities;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                ", gender=" + gender +
                ", carEntities=" + carEntities +
                '}';
    }

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

    public User.Gender getGender() {
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

}
