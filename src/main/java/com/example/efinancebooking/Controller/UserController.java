package com.example.efinancebooking.Controller;

import com.example.efinancebooking.BookingObjectControllerClasess.ReviewRequest;
import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Services.UserServices;
import com.example.efinancebooking.UserRequests.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path="/User")
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity<?> Register(@Valid @RequestBody AddUserRequest user){
        userServices.Register(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get")
    public @ResponseBody ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userServices.getUsers());
    }
    @PutMapping(path = "/rate")
    public void Rate(@RequestParam int bid,@RequestBody ReviewRequest Rate){
        userServices.Rate(bid, Rate);
    }

    @RequestMapping(path = "/hello")
    public String hello(){
        return "pf_.xhtml";
    }


}
