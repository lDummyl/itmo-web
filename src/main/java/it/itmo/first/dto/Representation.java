package it.itmo.first.dto;


import java.time.LocalDate;

/**
 * Добавить поля email, birthbate LocalDate, Enum Gender.
 *
 * */
public class Representation {
    public enum Gender {male, female}

    private Integer id;
    private String name;
    private String email;
    private LocalDate birthdate;
    private Gender gender;

    @Override
    public String toString() {
        return "Representation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                ", gender=" + gender +
                '}';
    }

    public Representation(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Representation(){

    }

    public Integer getId() {
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
