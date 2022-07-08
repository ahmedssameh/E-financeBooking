package com.example.efinancebooking.Services;

import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Repos.UserRepo;
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

    @Transactional
    public String Register(User user){
        userRepo.save(user);
        return "Registration is done";
    }
    public List<User> getUsers(){
        return userRepo.findAll();
    }
}
