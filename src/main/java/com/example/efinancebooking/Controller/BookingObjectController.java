package com.example.efinancebooking.Controller;

import com.example.efinancebooking.Model.BookingObject;
import com.example.efinancebooking.Services.BookingObjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path="/Booking")

public class BookingObjectController {

    @Autowired
    BookingObjectsService BookingObjService;
    @PostMapping(path ="/Add" )
    public @ResponseBody String addBookingObj(@Valid @RequestBody BookingObject BookingObj){
        BookingObjService.addNewBookObj(BookingObj);
        return "Creating Ad is done";
    }

    @GetMapping(path= "/Get")
    public @ResponseBody List<BookingObject> getAllBookingObj(){
        return BookingObjService.getAllBookingObj();
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody String delete(@RequestParam int bid){
        BookingObjService.delete(bid);
        return "BookingObject is deleted";
    }

    @PostMapping(path = "/assign")
    public @ResponseBody String assign(@RequestParam int uid,@RequestParam int bid){
        BookingObjService.AssignBook(uid,bid);
        return "assigned is done";
    }

    @DeleteMapping(path = "/cancel")
    public @ResponseBody String cancel(@RequestParam int uid,@RequestParam int bid){
        if(BookingObjService.CancelConstraint(bid)) {
            BookingObjService.cancel(uid, bid);
            return "assigned is Cancelled";
        }else{
            return "it cannot be cancelled";
        }
    }

    @GetMapping(path="/GetMyPublishedAds")
    public List<BookingObject> getMyAds(@RequestParam int uid){
        return BookingObjService.getMyAds(uid);
    }

}
