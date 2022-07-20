package com.example.efinancebooking.Model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int uid;

    @NotNull
    @Size(min = 3,max = 25)
    @Column(unique=true)
    private String Username;

    @NotNull
    private String password;

    @ManyToMany(fetch= FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @OneToMany(cascade = {CascadeType.ALL})
    private List<BookingObject> MyBookings=new ArrayList<>();

}
