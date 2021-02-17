package it.itmo.first.dto;

public class Car {
    private Integer id;
    private String model;
    private String brend;
    private Integer yearOfRelease;
    private Integer owner;

    public Car(Integer id, String model, String brend, Integer yearOfRelease, Integer owner) {
        this.id = id;
        this.model = model;
        this.brend = brend;
        this.yearOfRelease = yearOfRelease;
        this.owner = owner;
    }

    public Car() {
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

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }
}


