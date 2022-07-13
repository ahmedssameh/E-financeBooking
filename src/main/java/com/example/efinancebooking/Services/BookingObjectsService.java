package com.example.efinancebooking.Services;


import com.example.efinancebooking.Model.BookingObject;
import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Repos.BookingObjectRepo;
import com.example.efinancebooking.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@Service
public class BookingObjectsService {


    @Autowired
    BookingObjectRepo bookingObjectRepo;

    @Autowired
    UserRepo userRepo;

    @Transactional
    public void addNewBookObj(BookingObject Bobj){
        bookingObjectRepo.save(Bobj);
    }
    @Transactional
    public List<BookingObject> getAllBookingObj(){

        return bookingObjectRepo.findAll();
    }
    @Transactional
    public void delete(int bid){
        BookingObject Bobj= bookingObjectRepo.findBookingObjectByBid(bid);
        bookingObjectRepo.delete(Bobj);
    }
    @Transactional
    public List<BookingObject> getMyAds(int uid){
        return bookingObjectRepo.getMyAds(uid);
    }
    @Transactional
    public String AssignBook(int bid,int uid){
        BookingObject Bobj= bookingObjectRepo.findBookingObjectByBid(bid);
        User user= userRepo.findUserByUid(uid);
        int Quantity=Bobj.getQuantity();
        if(Quantity>0) {
            Bobj.setQuantity(Quantity-1);
        }
        user.getMyBookings().add(Bobj);
        userRepo.save(user);
        bookingObjectRepo.save(Bobj);
        return "Booking is done";
    }
    @Transactional
    public String cancel(int bid,int uid){
        BookingObject Bobj= bookingObjectRepo.findBookingObjectByBid(bid);
        User user= userRepo.findUserByUid(uid);
        int Quantity=Bobj.getQuantity()+1;
        Bobj.setQuantity(Quantity);
        user.getMyBookings().remove(Bobj);
        userRepo.save(user);
        bookingObjectRepo.save(Bobj);
        return "Booking is Cancelled";
    }
    @Transactional
    public boolean CancelConstraint(int bid){
        BookingObject Bobj= bookingObjectRepo.findBookingObjectByBid(bid);
        return Bobj.getPrice() > 100;
    }
}
