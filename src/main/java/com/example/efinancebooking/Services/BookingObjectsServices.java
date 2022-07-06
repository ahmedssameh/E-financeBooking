package com.example.efinancebooking.Services;


import com.example.efinancebooking.Model.BookingObject;
import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Repos.BookingObjectRepo;
import com.example.efinancebooking.Repos.UserRepo;

import java.util.Optional;

public class BookingObjectsServices {
    BookingObjectRepo getMeBookData;
    UserRepo getMeUserData;
    public String AssignBook(int bid,int uid){
        BookingObject Bobj=getMeBookData.findBookingObjectByBid(bid);
        User user=getMeUserData.findUserByUid(uid);
        user.getMyBookings().add(Bobj);
        getMeUserData.save(user);
        return "Booking is done";

    }
}
