package com.example.efinancebooking.Model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class reviewId implements Serializable {

    @ManyToOne
    private User user;
    @ManyToOne
    private BookingObject bookingObject;

    public BookingObject getBookingObject() {
        return bookingObject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBookingObject(BookingObject bookingObject) {
        this.bookingObject = bookingObject;
    }

    public reviewId(User user, BookingObject bookingObject) {
        this.user = user;
        this.bookingObject = bookingObject;
    }

    public reviewId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof reviewId)) return false;
        reviewId reviewId = (reviewId) o;
        return Objects.equals(user, reviewId.user) && Objects.equals(bookingObject, reviewId.bookingObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, bookingObject);
    }
}
