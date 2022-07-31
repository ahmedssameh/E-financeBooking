package com.example.efinancebooking.Controller;

import com.example.efinancebooking.BookingRequests.ReviewRequest;
import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Services.UserService;
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


import javax.servlet.http.HttpServletRequest;
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
    UserService userServices;

    ReviewRequest reviewRequest=new ReviewRequest();
    AddUserRequest addUserRequest = new AddUserRequest();
    @PostMapping(path="/add")
    public @ResponseBody String Register(@Valid @RequestBody AddUserRequest user){
        userServices.Register(user);
        addUserRequest = new AddUserRequest();
        return "/login.xhtml?faces-redirect=true";
    }
    public @ResponseBody boolean isLoggedIn(HttpServletRequest request){
        return request.getRemoteUser() != null;
    }
    @GetMapping(path = "/get")
    public @ResponseBody ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userServices.getUsers());
    }
    @PutMapping(path = "/rate")
    public @ResponseBody ResponseEntity<?> Rate(HttpServletRequest request, @RequestBody ReviewRequest Rate){
        userServices.Rate(request, Rate);
        reviewRequest=new ReviewRequest();
        return ResponseEntity.ok().build();
    }



}
