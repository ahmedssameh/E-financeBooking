package com.example.efinancebooking.Controller;

import com.example.efinancebooking.Model.Role;
import com.example.efinancebooking.Services.RoleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/Role")
public class RoleController {
    @Autowired
    RoleService roleServices;

    @PostMapping(path="/add")
    public @ResponseBody String AddRole(@Valid @RequestBody Role role){
        roleServices.AddRole(role);
        return "Role added";
    }
    @GetMapping(path = "/get")
    public @ResponseBody List<Role> getRoles(){
        return roleServices.getRoles();
    }

    @PostMapping(path="/addToUser")
    public @ResponseBody String AddRoleToUser(@Valid @RequestBody RoleUserMapper roleUserMapper){
        roleServices.AddRoleToUser(roleUserMapper.getUsername(), roleUserMapper.getRolename());
        return "Role " + roleUserMapper.getRolename() +  " added to " + roleUserMapper.getUsername();
    }
    @Data
    private static class RoleUserMapper{
        String username;
        String rolename;
    }



}
