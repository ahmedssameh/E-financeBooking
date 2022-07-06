package com.example.efinancebooking.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    int uid;
    private String Username;

    @Getter
    @Setter
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(referencedColumnName = "uid")
    private List<BookingObject> MyBookings=new ArrayList<>();

}
