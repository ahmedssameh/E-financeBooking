package com.example.efinancebooking.Model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data
public class User {
    public int getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @NotNull
    @Size(min = 3,max = 25)
    @Column(unique=true)
    private String Username;



    @ManyToMany(fetch= FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();



    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @Autowired
    private List<Booking> MyBookings;

    @NotNull
    @Size(min =8)
    private String Password;


    @NotNull
    @Email
    private String Email;

    @NotNull
    private String PhoneNumber;


    public User(String username, String password, String email, String phoneNumber) {
        Username = username;
        Password = password;
        Email = email;
        PhoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public List<Booking> getMyBookings() {
        return MyBookings;
    }

    public void setMyBookings(List<Booking> myBookings) {
        MyBookings = myBookings;
    }

}
