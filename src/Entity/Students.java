package Entity;

import javafx.beans.property.SimpleStringProperty;

public class Students {
    private int id;
    private String  name;
    private String  sex;
    private String class_room;
    private String  number;

    public Students() {

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClass_room() {
        return class_room;
    }

    public void setClass_room(String class_room) {
        this.class_room = class_room;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Students(String name, String sex, String class_room, String number) {
        this.name = name;
        this.sex = sex;
        this.class_room = class_room;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name=" + name +
                ", sex=" + sex +
                ", class_room=" + class_room +
                ", number=" + number +
                '}';
    }
}
