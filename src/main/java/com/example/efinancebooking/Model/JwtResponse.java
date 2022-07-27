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

    @Entity
    public static class BookingEnum {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;

        private String type;


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }



        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}