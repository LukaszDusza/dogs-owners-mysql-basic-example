package model;

import java.util.List;


public class OwnerDto {
    private String name;
    private List<String> dogs;

    public OwnerDto() { }

    public OwnerDto(String name, List<String> dogs) {
        this.name = name;
        this.dogs = dogs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDogs() {
        return dogs;
    }

    public void setDogs(List<String> dogs) {
        this.dogs = dogs;
    }

    @Override
    public String toString() {
        return "owner: " + name + ", " + "dogs: " + dogs;
    }
}
