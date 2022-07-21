package com.example.efinancebooking.enums;

import javax.persistence.*;

@Entity
public class BookingEnum {
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
