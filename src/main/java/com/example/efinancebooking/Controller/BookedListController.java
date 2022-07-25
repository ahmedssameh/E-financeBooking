package com.example.efinancebooking.Controller;

import com.example.efinancebooking.Model.BookingObject;
import com.example.efinancebooking.Repos.BookingObjectRepo;
import com.example.efinancebooking.Repos.UserRepo;
import lombok.Data;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Scope(value = "session")
@Component(value = "bookedList")
@ELBeanName(value = "bookedList")
@Join(path = "/bookedList", to = "/booked-list.jsf")
@Data
public class BookedListController {
    @Autowired
    private BookingObjectRepo bookingObjectRepo;
    @Autowired
    private UserRepo userRepo;

    private List<BookingObject> booked= new ArrayList<>();

    private BookingObject selectedBook;


    public List<BookingObject> init(int uid) {

        return userRepo.findUserByUid(uid).getMyBookings();
    }

    public void setData(){

    }
    public List<BookingObject> getBooked() {
        return booked;
    }

    public void setBooked(List<BookingObject> booked) {
        this.booked = booked;
    }

    public BookingObject getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(BookingObject selectedBook) {
        this.selectedBook = selectedBook;
    }
}
