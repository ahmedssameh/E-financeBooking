package com.example.efinancebooking.Services;


import com.example.efinancebooking.Model.BookingObject;
import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Repos.BookingObjectRepo;
import com.example.efinancebooking.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Configuration
@Service
public class BookingObjectsService {


    @Autowired
    BookingObjectRepo getMeBookData;

    @Autowired
    UserRepo getMeUserData;

    @Transactional
    public void addNewBookObj(BookingObject Bobj){
        getMeBookData.save(Bobj);
    }
    @Transactional
    public List<BookingObject> getAllBookingObj(){

        return getMeBookData.findAll();
    }
    @Transactional
    public void delete(int bid){
        BookingObject Bobj=getMeBookData.findBookingObjectByBid(bid);
        getMeBookData.delete(Bobj);
    }
    @Transactional
    public List<BookingObject> getMyAds(int uid){
        return getMeBookData.getMyAds(uid);
    }
    @Transactional
    public String AssignBook(int bid,int uid){
        BookingObject Bobj=getMeBookData.findBookingObjectByBid(bid);
        User user=getMeUserData.findUserByUid(uid);
        int Quantity=Bobj.getQuantity();
        if(Quantity>0) {
            Bobj.setQuantity(Quantity-1);
        }
        user.getMyBookings().add(Bobj);
        getMeUserData.save(user);
        getMeBookData.save(Bobj);
        return "Booking is done";
    }
    @Transactional
    public String cancel(int bid,int uid){
        BookingObject Bobj=getMeBookData.findBookingObjectByBid(bid);
        User user=getMeUserData.findUserByUid(uid);
        int Quantity=Bobj.getQuantity()+1;
        Bobj.setQuantity(Quantity);
        user.getMyBookings().remove(Bobj);
        getMeUserData.save(user);
        getMeBookData.save(Bobj);
        return "Booking is Cancelled";
    }
    @Transactional
    public boolean CancelConstraint(int bid){
        BookingObject Bobj=getMeBookData.findBookingObjectByBid(bid);
        return Bobj.getPrice() > 100;
    }
}
