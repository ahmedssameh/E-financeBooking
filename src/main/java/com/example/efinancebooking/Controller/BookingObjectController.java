package com.example.efinancebooking.Controller;

import com.example.efinancebooking.BookingRequests.EditBookingReq;
import com.example.efinancebooking.BookingRequests.AddBookingReq;
import com.example.efinancebooking.BookingRequests.FilteredBookingReq;
import com.example.efinancebooking.Model.Booking;
import com.example.efinancebooking.Services.BookingService;
import lombok.Data;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Data
@RestController
@RequestMapping(path="/Booking")
@Component(value = "BookingObjectController")
@ELBeanName(value = "BookingObjectController")
@Join(path = "/Booking", to = "/product-form.jsf")
public class BookingObjectController {

    @Autowired
    private BookingService BookingService;

    public AddBookingReq addBookingReq = new AddBookingReq();

    public EditBookingReq editBookingReq = new EditBookingReq();

//    private List<BookingObject> myPublishedAds=new ArrayList<>();



    @PostMapping(path ="/Add" )
    public @ResponseBody String addBooking(HttpServletRequest request){
        BookingService.addNewBookObj(addBookingReq,request);
        addBookingReq =new AddBookingReq();
        return "/Published-ads.xhtml?faces-redirect=true";
    }

    @GetMapping(path= "/Get")
    public @ResponseBody ResponseEntity<List<Booking>> getAllBookings(){
        return ResponseEntity.ok().body(BookingService.getAllBookingObj());
    }

    @GetMapping(path= "/GetMyBookings")
    public @ResponseBody ResponseEntity<List<Booking>> getMyBookings(@RequestParam int uid){
        return ResponseEntity.ok().body(BookingService.getUserBookings(uid));
    }




    @PostMapping(path = "/assign")
    public @ResponseBody ResponseEntity<?> assign(@RequestParam HttpServletRequest request,@RequestParam int bid){
        BookingService.AssignBook(request,bid);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/cancel")
    public @ResponseBody ResponseEntity<?> cancel(@RequestParam HttpServletRequest request,@RequestParam int bid){
        if(BookingService.CancelConstraint(bid)) {
            BookingService.cancel(request, bid);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/edit")
    public void EditBookingPost(@RequestBody EditBookingReq editBookingObjReq){
        System.out.println("Heyyyyyyyyyyyyyyyyyyyyyy");
        BookingService.EditBookingPost(editBookingObjReq);
        this.editBookingReq =new EditBookingReq();

    }
    @GetMapping(path = "/filter")
    public ResponseEntity<List<Booking>> getAdsFiltered(@RequestBody FilteredBookingReq getAdsFilteredReq){
        return ResponseEntity.ok().body(BookingService.getAdsFiltered(getAdsFilteredReq));
    }

    @GetMapping(path = "/SearchByName")
    public ResponseEntity<List<Booking>> getAdsFilteredByName(@RequestParam String FindMe){
        return ResponseEntity.ok().body(BookingService.SearchByName(FindMe));
    }

    @GetMapping(path = "/SearchByLocation")
    public ResponseEntity<List<Booking>> getAdsFilteredByLocation(@RequestParam String FindMe){
        return ResponseEntity.ok().body(BookingService.SearchByLocation(FindMe));
    }
}
