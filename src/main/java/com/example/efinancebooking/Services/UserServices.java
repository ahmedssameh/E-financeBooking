package com.example.efinancebooking.Services;

import com.example.efinancebooking.Model.Role;
import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Repos.RoleRepo;
import com.example.efinancebooking.Repos.UserRepo;
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

    @Transactional
    public String Register(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "Registration is done"; //TODO: return URI with 201 status code
    }
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
}
