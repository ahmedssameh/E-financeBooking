package com.example.efinancebooking.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    int id;
    @NotNull
    @Size(min = 3,max = 25)
    private String Username;

    @OneToMany(cascade = {CascadeType.ALL})
    @Autowired
    private List<BookingObject> MyBookings;


    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public List<BookingObject> getMyBookings() {
        return MyBookings;
    }

    public void setMyBookings(List<BookingObject> myBookings) {
        MyBookings = myBookings;
    }

}
