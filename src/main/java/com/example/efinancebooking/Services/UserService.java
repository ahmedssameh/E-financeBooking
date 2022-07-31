package com.example.efinancebooking.Services;

import com.example.efinancebooking.BookingRequests.ReviewRequest;
import com.example.efinancebooking.Model.Booking;
import com.example.efinancebooking.Model.Review;
import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Model.reviewId;
import com.example.efinancebooking.Repos.BookingRepo;
import com.example.efinancebooking.Repos.ReviewRepo;
import com.example.efinancebooking.Repos.UserRepo;
import com.example.efinancebooking.UserRequests.AddUserRequest;
import com.example.efinancebooking.config.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Autowired
   private final PasswordEncoder passwordEncoder;
    @Autowired
    BookingRepo bookingObjectRepo;
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    @Transactional
    public String Register(AddUserRequest AddedUser){
        String encodedPassword = passwordEncoder.encode(AddedUser.getPassword());
        User user= new User(AddedUser.getUsername(),encodedPassword, AddedUser.getEmail(),AddedUser.getPhoneNumber());
        userRepo.save(user);//TODO: add email to the Registration
        return "Registration is done"; //TODO: return URI with 201 status code
    }
//    @Transactional
//    public User GetRegisteredUser(){
//
//    }
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    @Override @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByName(username);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role->{
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);
    }

    @Transactional
    public void Rate(HttpServletRequest request, ReviewRequest reviewRequest) {
        User user = this.userRepo.findUserByName(request.getRemoteUser());
        Booking bookingObject = this.bookingObjectRepo.findBookingObjectByBid(reviewRequest.bookingObjId);
        Review review = new Review();
        review.setRate(reviewRequest.rate);
        review.setComment(reviewRequest.comment);
        reviewId reviewId = new reviewId(user, bookingObject);
        review.setId(reviewId);
        Booking Rated = this.bookingObjectRepo.findBookingObjectByBid(reviewRequest.bookingObjId);
        Rated.setAvgRate(((double) reviewRequest.rate + Rated.getAvgRate() * (double)Rated.getNumberOfRates()) / (double)(Rated.getNumberOfRates() + 1));
        Rated.setNumberOfRates(Rated.getNumberOfRates() + 1);
        this.reviewRepo.save(review);
        this.bookingObjectRepo.save(Rated);
    }

}
