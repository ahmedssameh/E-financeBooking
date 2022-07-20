package com.example.efinancebooking;

import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Services.UserServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication

public class EFinanceBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EFinanceBookingApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




//    @Bean
//    CommandLineRunner run (UserServices userServices){
//        return args -> {
//          userServices.Register(new User(6, "Hassan", "1234", null));
//        };
//    }

}
