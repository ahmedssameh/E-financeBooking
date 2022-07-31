package com.example.efinancebooking.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private String jwttoken;

    public JwtResponse() {
    }
    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }


}