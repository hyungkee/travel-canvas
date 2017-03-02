package app.heuristy.dev.travelcanvas.model;

import java.io.Serializable;

/**
 * Created by Heuristy10 on 2017-03-02.
 */

public class Person implements Serializable{
    public int id;
    public String name;
    public String phone;

    public Person(){

    }

    public Person(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
