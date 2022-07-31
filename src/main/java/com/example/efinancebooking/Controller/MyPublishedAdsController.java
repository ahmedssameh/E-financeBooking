package com.example.efinancebooking.Controller;


import com.example.efinancebooking.Model.Booking;
import com.example.efinancebooking.Services.BookingService;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Scope(value = "session")
@Component(value = "MyPublishedAdsController")
@ELBeanName(value = "MyPublishedAdsController")
@Join(path = "/MyPublishedAds", to = "/Published-ads.jsf")
public class MyPublishedAdsController {

    @Autowired
    private BookingService bookingService;

    @GetMapping(path="/GetMyPublishedAds")
    public List<Booking> getMyBookings(HttpServletRequest request){

        return bookingService.getPublisherBookings(request);
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody String delete(@RequestParam int bid){
        bookingService.delete(bid);
        return "/Published-ads.xhtml?faces-redirect=true";
    }
}
