package com.example.efinancebooking.Services;

import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserServices implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Transactional
    public String Register(User user){
        userRepo.save(user);
        return "Registration is done"; //TODO: return URI with 201 status code
    }
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByName(username);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),null);
    }
}
