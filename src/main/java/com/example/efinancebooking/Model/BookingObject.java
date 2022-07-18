package com.example.efinancebooking.Model;

import com.example.efinancebooking.enums.BookingEnum;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;


@Entity
public class BookingObject implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @Autowired
    private BookingEnum type;
    private java.sql.Date PublishedDate;
    @NotNull
    @Size(min=10)
    private String Description;
    @NotNull
    private double Price;
    @NotNull
    private int Quantity;

    @NotNull
    private String Name;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Autowired
    @NotNull
    private  User Publisher;

    private String Location;
    public BookingObject() {

    }
    public BookingObject(String Location,String name,BookingEnum type, Date publishedDate, String description, double price, int quantity, User publisher) {
        this.Location=Location;
        Name=name;
        this.type = type;
        PublishedDate = publishedDate;
        Description = description;
        Price = price;
        Quantity = quantity;
        Publisher = publisher;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public User getPublisher() {
        return Publisher;
    }

    public void setPublisher(User publisher) {
        Publisher = publisher;
    }
    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Date getPublishedDate() {
        return PublishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        PublishedDate = publishedDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int bid) {
        this.id = bid;
    }

    public BookingEnum getType() {
        return type;
    }

    public void setType(BookingEnum type) {
        this.type = type;
    }
}
