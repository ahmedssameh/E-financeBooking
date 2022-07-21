package com.example.efinancebooking.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Review {
    @Id
    @Column
    private Integer id;

    private double Rate=0;

    private String Comment;

    private int NumberOfRates=0;

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double rate) {
        Rate = rate;
    }


    public int getNumberOfRates() {
        return NumberOfRates;
    }

    public void setNumberOfRates(int numberOfRates) {
        NumberOfRates = numberOfRates;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
