package com.example.efinancebooking.Controller;

import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/User")
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping(path="/add")
    public @ResponseBody String Register(@Valid @RequestBody User user){
        userServices.Register(user);
        return "Registering is done";
    }

    @GetMapping(path = "/get")
    public @ResponseBody List<User> getUsers(){
        return userServices.getUsers();
    }
}
