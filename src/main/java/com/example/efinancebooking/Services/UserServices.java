package com.example.efinancebooking.Services;

import com.example.efinancebooking.BookingObjectControllerClasess.ReviewRequest;
import com.example.efinancebooking.Model.BookingObject;
import com.example.efinancebooking.Model.Review;
import com.example.efinancebooking.Model.Role;
import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Repos.BookingObjectRepo;
import com.example.efinancebooking.Repos.ReviewRepo;
import com.example.efinancebooking.Repos.RoleRepo;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor
public class UserServices implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Autowired
   private final PasswordEncoder passwordEncoder;
    @Autowired
    BookingObjectRepo bookingObjectRepo;
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
