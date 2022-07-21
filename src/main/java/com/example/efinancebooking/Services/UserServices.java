package com.example.efinancebooking.Services;

import com.example.efinancebooking.BookingObjectControllerClasess.ReviewRequest;
import com.example.efinancebooking.Model.BookingObject;
import com.example.efinancebooking.Model.Review;
import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Repos.BookingObjectRepo;
import com.example.efinancebooking.Repos.ReviewRepo;
import com.example.efinancebooking.Repos.UserRepo;
import com.example.efinancebooking.UserRequests.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserServices {
    @Autowired
    UserRepo userRepo;

    @Autowired
    BookingObjectRepo bookingObjectRepo;

    @Autowired
    ReviewRepo reviewRepo;

    @Transactional
    public String Register(AddUserRequest AddedUser){
        User user= new User(AddedUser.UserName,AddedUser.Password, AddedUser.Seller,
                AddedUser.Email,AddedUser.PhoneNumber);
        userRepo.save(user);
        return "Registration is done";
    }
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    @Transactional
    public void Rate(int bid, ReviewRequest Rate){
        Review rate=new Review();
        rate.setRate(Rate.Rate);
        rate.setComment(Rate.Comment);
        BookingObject Rated=bookingObjectRepo.findBookingObjectByBid(bid);
        rate.setNumberOfRates(Rated.getRate().getNumberOfRates());
        reviewRepo.save(rate);
        Rated.getRate().setRate(Rate.Rate);
        Rated.getRate().setComment(Rate.Comment);
        //my rate=5 new Rate=5 number=100
        Rated.getRate().setRate((Rate.Rate+(Rated.getRate().getRate()*Rated.getRate().getNumberOfRates()))/Rated.getRate().getNumberOfRates()+1);
        Rated.getRate().setNumberOfRates(Rated.getRate().getNumberOfRates()+1);
    }

}
