//package com.example.efinancebooking.Controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletResponse;
//
//@RestController
//@Controller
//public class LogoutController {
//
//    @GetMapping(value = "/bara")
//    public @ResponseBody String logout(HttpServletResponse response){
//        Cookie deleteServletCookie = new Cookie("access_token", null);
//        deleteServletCookie.setMaxAge(0);
//        response.addCookie(deleteServletCookie);
//        return "/product-form.xhtml?faces-redirect=true";
//    }
//}
