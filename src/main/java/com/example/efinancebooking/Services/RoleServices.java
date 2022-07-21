package com.example.efinancebooking.Services;

import com.example.efinancebooking.Model.Role;
import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Repos.RoleRepo;
import com.example.efinancebooking.Repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServices {

    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRepo userRepo;

    @Transactional
    public String AddRole(Role role){
        roleRepo.save(role);
        return "Role added successfully";
    }
    @Transactional
    public List<Role> getRoles(){
        return roleRepo.findAll();
    }

    @Transactional
    public String AddRoleToUser(String userName, String roleName){
        User user = userRepo.findUserByName(userName);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);

        return "Role "+ roleName + " added to "+ userName;
    }
}
