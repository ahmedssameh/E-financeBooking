package com.example.efinancebooking.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookingType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}