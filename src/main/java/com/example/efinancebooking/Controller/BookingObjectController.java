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
    private BookingObjectsService BookingObjService;

    public addBookingObjReq addBookingObjReq = new addBookingObjReq();

    public EditBookingObjReq editBookingObjReq= new EditBookingObjReq();

//    private List<BookingObject> myPublishedAds=new ArrayList<>();



    @PostMapping(path ="/Add" )
    public @ResponseBody String addBookingObj(HttpServletRequest request){
        BookingObjService.addNewBookObj(addBookingObjReq,request);
        addBookingObjReq=new addBookingObjReq();
        return "/Published-ads.xhtml?faces-redirect=true";
    }

    @GetMapping(path= "/Get")
    public @ResponseBody ResponseEntity<List<BookingObject>> getAllBookingObj(){
        return ResponseEntity.ok().body(BookingObjService.getAllBookingObj());
    }

    @GetMapping(path= "/GetMyBookings")
    public @ResponseBody ResponseEntity<List<BookingObject>> getMyBookings(@RequestParam int uid){
        return ResponseEntity.ok().body(BookingObjService.getUserBookings(uid));
    }




    @PostMapping(path = "/assign")
    public @ResponseBody ResponseEntity<?> assign(@RequestParam HttpServletRequest request,@RequestParam int bid){
        BookingObjService.AssignBook(request,bid);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/cancel")
    public @ResponseBody ResponseEntity<?> cancel(@RequestParam HttpServletRequest request,@RequestParam int bid){
        if(BookingObjService.CancelConstraint(bid)) {
            BookingObjService.cancel(request, bid);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/edit")
    public void EditBookingPost(@RequestBody EditBookingObjReq editBookingObjReq){
        System.out.println("Heyyyyyyyyyyyyyyyyyyyyyy");
        BookingObjService.EditBookingPost(editBookingObjReq);
        this.editBookingObjReq=new EditBookingObjReq();

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
