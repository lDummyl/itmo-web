package it.itmo.first.dto;

public class Car {
    private Integer id;
    private String model;
    private String brend;
    private Integer year_of_release;
    private Integer owner_id;

    public Car(Integer id, String model, String brend, Integer year_of_release, Integer owner_id) {
        this.id = id;
        this.model = model;
        this.brend = brend;
        this.year_of_release = year_of_release;
        this.owner_id = owner_id;
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

    public Integer getYear_of_release() {
        return year_of_release;
    }

    public void setYear_of_release(Integer year_of_release) {
        this.year_of_release = year_of_release;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }
}


