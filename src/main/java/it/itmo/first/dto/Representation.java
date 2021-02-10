package it.itmo.first.dto;


/**
 * Добавить поля email, birthbate LocalDate, Enum Gender.
 *
 * */
public class Representation {

    private Integer id;
    private String name;

    public Representation(Integer id, String name) {
        this.id = id;
        this.name = name;
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
