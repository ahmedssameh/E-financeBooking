package com.example.efinancebooking.Model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class reviewId implements Serializable {

    @ManyToOne
    private User user;
    @ManyToOne
    private Booking booking;

    public Booking getBooking() {
        return booking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public reviewId(User user, Booking bookingObject) {
        this.user = user;
        this.booking = bookingObject;
    }

    public reviewId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof reviewId)) return false;
        reviewId reviewId = (reviewId) o;
        return Objects.equals(user, reviewId.user) && Objects.equals(booking, reviewId.booking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, booking);
    }
}
