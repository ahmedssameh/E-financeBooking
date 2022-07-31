package com.example.efinancebooking.Controller;

import com.example.efinancebooking.Model.Booking;
import com.example.efinancebooking.Repos.BookingRepo;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@Scope (value = "session")
@Component (value = "productList")
@ELBeanName(value = "productList")
@Join(path = "/", to = "/product-list.jsf")
public class ProductListController {
    @Autowired
    private BookingRepo bookingRepo;

    private List<Booking> bookings;
    private Booking selectedBooking;



    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        bookings = bookingRepo.findActiveBookingObject();
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public Booking getSelectedBooking() {
        return selectedBooking;
    }

    public void setSelectedBooking(Booking selectedBooking) {
        this.selectedBooking = selectedBooking;
    }

    public void onRowSelect(SelectEvent<Booking> event) {
        FacesMessage msg = new FacesMessage("Product Selected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent<Booking> event) {
        FacesMessage msg = new FacesMessage("Product Unselected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}