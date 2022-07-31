package com.example.efinancebooking.Services;


import com.example.efinancebooking.BookingRequests.EditBookingReq;
import com.example.efinancebooking.BookingRequests.AddBookingReq;
import com.example.efinancebooking.BookingRequests.FilteredBookingReq;
import com.example.efinancebooking.Model.Booking;
import com.example.efinancebooking.Model.BookingType;
import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Repos.BookingRepo;
import com.example.efinancebooking.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Configuration
@Service
public class BookingService {


    @Autowired
    BookingRepo bookingObjectRepo;

    @Autowired
    UserRepo userRepo;

    @Transactional
    public void addNewBookObj(AddBookingReq addBookingObjReq, HttpServletRequest request){
        User u = userRepo.findUserByName(request.getRemoteUser());
        BookingType bookingTypes =bookingObjectRepo.findBookingTypeByName(addBookingObjReq.type);
        Booking b = new Booking(addBookingObjReq.Quantity,addBookingObjReq.Location,
                addBookingObjReq.Name,
                bookingTypes,
                addBookingObjReq.PublishedDate,
                addBookingObjReq.Description,
                addBookingObjReq.Price,
                addBookingObjReq.Quantity,
                u);
        bookingObjectRepo.save(b);
    }
    @Transactional
    public List<Booking> getAllBookingObj(){

        return bookingObjectRepo.findAll();
    }
    @Transactional
    public List<Booking> getAdsFiltered(FilteredBookingReq getAdsFilteredReq){
         double minPrice = getAdsFilteredReq.minPrice;
         double maxPrice = getAdsFilteredReq.maxPrice;
         int minQuantity = getAdsFilteredReq.minQuantity;
        if(getAdsFilteredReq.maxPrice==0){
            getAdsFilteredReq.maxPrice=999999999;
        }
        if(getAdsFilteredReq.type!=null){
            String type = getAdsFilteredReq.type;
            return bookingObjectRepo.findBookingObjectFilteredWithType(minPrice,maxPrice,minQuantity,type);
         }
        return bookingObjectRepo.findBookingObjectFiltered(minPrice,maxPrice,minQuantity);
    }

    @Transactional
    public void delete(int bid){
        Booking Bobj= bookingObjectRepo.findBookingObjectByBid(bid);
        if(DeleteConstraint(bid)){
            Bobj.setStatus("deleted");
            bookingObjectRepo.save(Bobj);
        }
    }
    @Transactional
    public List<Booking> getPublisherBookings(HttpServletRequest request){

        return bookingObjectRepo.getPublisherBookings(userRepo.findUserByName(request.getRemoteUser()).getId());
    }

    @Transactional
    public List<Booking> getUserBookings(int uid){

        User user=userRepo.findUserByUid(uid);
        return user.getMyBookings();
    }

    @Transactional
    public String AssignBook(HttpServletRequest request,int bid){
        Booking Bobj= bookingObjectRepo.findBookingObjectByBid(bid);
        User user= userRepo.findUserByName(request.getRemoteUser());
        if(!user.getMyBookings().contains(Bobj)){
            int Quantity=Bobj.getQuantity();
            if(Quantity>0) {
                Bobj.setQuantity(Quantity-1);
                user.getMyBookings().add(Bobj);
                userRepo.save(user);
                bookingObjectRepo.save(Bobj);
                return "Booking is done";
            }
            else
                return "no available quantity";

        }
        else
            return "You have already book this";
    }

    @Transactional
    public String cancel(HttpServletRequest request,int bid){
        Booking Bobj= bookingObjectRepo.findBookingObjectByBid(bid);
        User user= userRepo.findUserByName(request.getRemoteUser());
        int Quantity=Bobj.getQuantity()+1;
        Bobj.setQuantity(Quantity);
        user.getMyBookings().remove(Bobj);
        userRepo.save(user);
        bookingObjectRepo.save(Bobj);
        return "Booking is Cancelled";
    }

    @Transactional
    public boolean CancelConstraint(int bid){
        Booking Bobj= bookingObjectRepo.findBookingObjectByBid(bid);
        return Bobj.getPrice() > 100;
    }

    @Transactional
    public boolean DeleteConstraint(int bid){
        Booking Bobj= bookingObjectRepo.findBookingObjectByBid(bid);
        return Bobj.getQuantity()==Bobj.getOriginalQuantity();
    }

    @Transactional
    public void EditBookingPost(EditBookingReq EditBookingObjReq){
        Booking BObj= bookingObjectRepo.findBookingObjectByBid(EditBookingObjReq.id);
        System.out.println(EditBookingObjReq.Price);
        System.out.println(EditBookingObjReq.Quantity);
        System.out.println(EditBookingObjReq.Description);
        BObj.setQuantity(EditBookingObjReq.Quantity);
        BObj.setPrice(EditBookingObjReq.Price);
        BObj.setDescription(EditBookingObjReq.Description);
        bookingObjectRepo.save(BObj);
    }

    @Transactional
    public List<Booking> SearchByName(String FindMe){
        FindMe='%'+FindMe+'%';
        return bookingObjectRepo.findBookingObjectFilteredByName(FindMe);
    }

    @Transactional
    public List<Booking> SearchByLocation(String FindMe){
        FindMe='%'+FindMe+'%';
        return bookingObjectRepo.findBookingObjectFilteredByLocation(FindMe);
    }
}
