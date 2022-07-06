package com.example.efinancebooking.Controller;

import com.example.efinancebooking.Model.BookingObject;
import com.example.efinancebooking.Repos.BookingObjectRepo;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/Booking")
public class BookingObjectController {

    private BookingObjectRepo BookingObjRepo;

    @PostMapping(path ="/Add" )
    public @ResponseBody String addBookingObj(@RequestBody BookingObject BookingObj){
        BookingObjRepo.save(BookingObj);
        return "Creating Ad is done";
    }

    @GetMapping(path= "/Get")
    public @ResponseBody Iterable<BookingObject> getAllBookingObj(){
        return BookingObjRepo.findAll();
    }
}
