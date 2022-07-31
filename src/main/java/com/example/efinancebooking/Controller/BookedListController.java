package com.example.efinancebooking.Controller;

import com.example.efinancebooking.Model.Booking;
import com.example.efinancebooking.Repos.BookingRepo;
import com.example.efinancebooking.Repos.UserRepo;
import lombok.Data;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Scope(value = "session")
@Component(value = "bookedList")
@ELBeanName(value = "bookedList")
@Join(path = "/bookedList", to = "/booked-list.jsf")
@Data
public class BookedListController {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private UserRepo userRepo;

    private List<Booking> booked= new ArrayList<>();

    private Booking selectedBook;


    public List<Booking> init(HttpServletRequest request) {

        return userRepo.findUserByName(request.getRemoteUser()).getMyBookings();
    }

    public void setData(){

    }
    public List<Booking> getBooked() {
        return booked;
    }

    public void setBooked(List<Booking> booked) {
        this.booked = booked;
    }

    public Booking getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Booking selectedBook) {
        this.selectedBook = selectedBook;
    }
}
