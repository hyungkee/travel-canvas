package app.heuristy.dev.travelcanvas.model;

import java.io.Serializable;

/**
 * Created by Heuristy10 on 2017-03-02.
 */

public class Person implements Serializable{
    public int id;
    public String name;
    public String number;

    public Person(){

    }

    public Person(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
