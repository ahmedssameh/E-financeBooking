package com.example.efinancebooking.Controller;

import com.example.efinancebooking.Model.BookingObject;
import com.example.efinancebooking.Services.BookingObjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
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
    public @ResponseBody ResponseEntity<?> addBookingObj(@Valid @RequestBody BookingObject BookingObj){
        BookingObjService.addNewBookObj(BookingObj);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path= "/Get")
    public @ResponseBody ResponseEntity<List<BookingObject>> getAllBookingObj(){
        return ResponseEntity.ok().body(BookingObjService.getAllBookingObj());
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody ResponseEntity<?> delete(@RequestParam int bid){
        BookingObjService.delete(bid);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/assign")
    public @ResponseBody ResponseEntity<?> assign(@RequestParam int uid,@RequestParam int bid){
        BookingObjService.AssignBook(uid,bid);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/cancel")
    public @ResponseBody ResponseEntity<?> cancel(@RequestParam int uid,@RequestParam int bid){
        if(BookingObjService.CancelConstraint(bid)) {
            BookingObjService.cancel(uid, bid);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path="/GetMyPublishedAds")
    public ResponseEntity<List<BookingObject>> getMyAds(@RequestParam int uid){
        return ResponseEntity.ok().body( BookingObjService.getMyAds(uid));
    }

}
