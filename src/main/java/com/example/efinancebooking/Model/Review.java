package com.example.efinancebooking.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review  {

    @EmbeddedId
    private reviewId id;

    private double Rate=0;

    private String Comment;



    public String getComment() {
        return Comment;
    }

    public reviewId getId() {
        return id;
    }

    public void setId(reviewId id) {
        this.id = id;
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


}
