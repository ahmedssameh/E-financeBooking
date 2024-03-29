package com.example.efinancebooking.Model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;


@Entity
@Data
public class Booking implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)

    @Autowired
    private BookingType type;
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
    private int NumberOfRates=0;

    private int originalQuantity;

    private String status = "active";
//    @OneToMany(cascade = CascadeType.ALL)
//    @Autowired
//    private List<Review> rate;
//
    private double avgRate = 0.0;

    public int getNumberOfRates() {
        return NumberOfRates;
    }

    public void setNumberOfRates(int numberOfRates) {
        NumberOfRates = numberOfRates;
    }


//    public List<Review> getAllRate() {
//        return rate;
//    }
//
//    public void addRate(Review rate) {
//        rate.add(rate);
//    }

    public int getIntRate() {
        return (int) avgRate;
    }

    public double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Booking() {

    }
    public Booking(int OriginalQuantity, String Location, String name, BookingType type, Date publishedDate, String description, double price, int quantity, User publisher) {
        this.originalQuantity=OriginalQuantity;
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

    public BookingType getType() {
        return type;
    }

    public void setType(BookingType type) {
        this.type = type;
    }
}
