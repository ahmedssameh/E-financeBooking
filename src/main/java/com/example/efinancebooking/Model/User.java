package com.example.efinancebooking.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
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

    @NotNull
    @Size(min =8)
    private String Password;


    @NotNull
    @Email
    private String Email;

    @NotNull
    private double PhoneNumber;


    public User(String username, String password, String email, double phoneNumber) {
        Username = username;
        Password = password;
        Email = email;
        PhoneNumber = phoneNumber;
    }

    public User() {

    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

//    public boolean isSeller() {
//        return Seller;
//    }
//
//    public void setSeller(boolean seller) {
//        Seller = seller;
//    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public double getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(double phoneNumber) {
        PhoneNumber = phoneNumber;
    }

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
