package com.example.efinancebooking.Controller;

import com.example.efinancebooking.BookingObjectControllerClasess.ReviewRequest;
import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Services.UserServices;
import com.example.efinancebooking.UserRequests.AddUserRequest;
import lombok.Data;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path="/User")
@Component(value = "UserController")
@Data

@ELBeanName(value = "signUp")
@Scope(value = "session")
@Join(path = "/signUp", to = "/signUp.jsf")
public class UserController {

    @Autowired
    UserServices userServices;

    ReviewRequest reviewRequest=new ReviewRequest();

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
    public @ResponseBody ResponseEntity<?> Rate(@RequestParam int uid,@RequestBody ReviewRequest Rate){
        userServices.Rate(uid, Rate);
        reviewRequest=new ReviewRequest();
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/hello")
    public String hello(){
        return "pf_.xhtml";
    }


}
