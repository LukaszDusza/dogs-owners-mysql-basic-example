package model;

import java.util.Date;

public class Dog {
    private int id;
    private String name;
    private int age;
    private String breed;
    private Date date;
    private int owner_id;


//    == gett sett con to ==

    public Dog(int id, String name, String breed, int age, Date date, int owner_id) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.date = date;
        this.owner_id = owner_id;
    }

    public Dog() {  }

    public Dog(int id, String name, int age, String breed, java.sql.Date date, int owner_id) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.date = date;
        this.owner_id = owner_id;
    }

    public Dog(String name, int age, String breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", date=" + date +
                ", owner_id=" + owner_id +
                '}';
    }
}
