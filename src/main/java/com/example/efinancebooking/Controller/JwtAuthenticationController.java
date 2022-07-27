package com.example.efinancebooking.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;
import java.util.Objects;

import com.example.efinancebooking.JwtService.JwtUserDetailsServices;
import com.example.efinancebooking.Model.JwtRequest;
import com.example.efinancebooking.Model.JwtResponse;
import com.example.efinancebooking.config.JwtTokenUtil;
import lombok.Data;
import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin
@Component(value = "JwtAuthenticationController")
@Join(path = "/login", to = "/login.jsf")
@Data

public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsServices userDetailsService;

    @Autowired
    private JwtRequest authenticationRequest;

    @Autowired
    private HttpServletResponse jwtResponse;

    @PostConstruct
    public void init() {
        this.authenticationRequest = new JwtRequest();

    }
    @GetMapping(value = "/bara")
    public @ResponseBody String logout(HttpServletResponse response){
        Cookie deleteServletCookie = new Cookie("access_token", null);
        deleteServletCookie.setMaxAge(0);
        response.addCookie(deleteServletCookie);
        return "/login.xhtml?faces-redirect=true";
    }
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String createAuthenticationToken(HttpServletResponse response) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
//        final String token_Bearer = "Bearer"+ token;
        JwtResponse jwtResponse= new JwtResponse(token);
        // create a cookie
        Cookie cookie = new Cookie("access_token", token);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.setHeader("Access-Control-Allow-Credentials", "true"); //TODO: check if should be set to true or not (production)
        //add cookie to response
        response.addCookie(cookie);
        response.addHeader("Authorization", "Bearer "+ token);
        authenticationRequest=new JwtRequest();
        return "/product-form.xhtml?faces-redirect=true";
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
