package com.example.efinancebooking.Controller;

import com.example.efinancebooking.BookingObjectControllerClasess.EditBookingObjReq;
import com.example.efinancebooking.BookingObjectControllerClasess.addBookingObjReq;
import com.example.efinancebooking.BookingObjectControllerClasess.getAdsFilteredReq;
import com.example.efinancebooking.Model.BookingObject;
import com.example.efinancebooking.Services.BookingObjectsService;
import lombok.Data;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@Data
@RestController
@RequestMapping(path="/Booking")
@Component(value = "BookingObjectController")
@ELBeanName(value = "BookingObjectController")
@Join(path = "/Booking", to = "/product-form.jsf")
public class BookingObjectController {

    @Autowired
    BookingObjectsService BookingObjService;

    public addBookingObjReq addBookingObjReq = new addBookingObjReq();

    @PostMapping(path ="/Add" )
    public @ResponseBody String addBookingObj(){
        BookingObjService.addNewBookObj(addBookingObjReq);
        addBookingObjReq=new addBookingObjReq();
        return "/product-list.xhtml?faces-redirect=true";
    }

    @GetMapping(path= "/Get")
    public @ResponseBody ResponseEntity<List<BookingObject>> getAllBookingObj(){
        return ResponseEntity.ok().body(BookingObjService.getAllBookingObj());
    }

    @GetMapping(path= "/GetMyBookings")
    public @ResponseBody ResponseEntity<List<BookingObject>> getMyBookings(@RequestParam int uid){
        return ResponseEntity.ok().body(BookingObjService.getMyBooked(uid));
    }

    @GetMapping(path="/GetMyPublishedAds")
    public ResponseEntity<List<BookingObject>> getMyAds(@RequestParam int uid){
        return ResponseEntity.ok().body( BookingObjService.getMyAds(uid));
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

    @PutMapping(path = "/edit")
    public void EditBookingPost(@RequestBody EditBookingObjReq editBookingObjReq){
        BookingObjService.EditBookingPost(editBookingObjReq);
    }
    @GetMapping(path = "/filter")
    public ResponseEntity<List<BookingObject>> getAdsFiltered(@RequestBody getAdsFilteredReq getAdsFilteredReq){
        return ResponseEntity.ok().body(BookingObjService.getAdsFiltered(getAdsFilteredReq));
    }

    @GetMapping(path = "/SearchByName")
    public ResponseEntity<List<BookingObject>> getAdsFilteredByName(@RequestParam String FindMe){
        return ResponseEntity.ok().body(BookingObjService.SearchByName(FindMe));
    }

    @GetMapping(path = "/SearchByLocation")
    public ResponseEntity<List<BookingObject>> getAdsFilteredByLocation(@RequestParam String FindMe){
        return ResponseEntity.ok().body(BookingObjService.SearchByLocation(FindMe));
    }
}
