package com.example.efinancebooking.Model;

import com.example.efinancebooking.enums.BookingEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity
public class BookingObject implements Serializable {
    @Id
    @GeneratedValue
    private int bid;
    private BookingEnum type;
    private java.sql.Date PublishedDate;
    private String Description;
    private double Price;
    private int Quantity;

    @ManyToOne
    @JoinColumn(referencedColumnName = "uid")
    private  User Publisher;


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

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public BookingEnum getType() {
        return type;
    }

    public void setType(BookingEnum type) {
        this.type = type;
    }
}
