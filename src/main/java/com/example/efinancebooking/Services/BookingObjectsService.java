package com.example.efinancebooking.Services;


import com.example.efinancebooking.BookingObjectControllerClasess.EditBookingObjReq;
import com.example.efinancebooking.BookingObjectControllerClasess.addBookingObjReq;
import com.example.efinancebooking.BookingObjectControllerClasess.getAdsFilteredReq;
import com.example.efinancebooking.Model.BookingObject;
import com.example.efinancebooking.Model.User;
import com.example.efinancebooking.Repos.BookingObjectRepo;
import com.example.efinancebooking.Repos.UserRepo;
import com.example.efinancebooking.enums.BookingEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@Service
public class BookingObjectsService {


    @Autowired
    BookingObjectRepo bookingObjectRepo;

    @Autowired
    UserRepo userRepo;

    @Transactional
    public void addNewBookObj(addBookingObjReq addBookingObjReq){
        User u = userRepo.findUserByUid(addBookingObjReq.userid);
        BookingEnum bookingEnum=bookingObjectRepo.findBookingTypeByName(addBookingObjReq.type);
        BookingObject b = new BookingObject(addBookingObjReq.Location,
                addBookingObjReq.Name,
                bookingEnum,
                addBookingObjReq.PublishedDate,
                addBookingObjReq.Description,
                addBookingObjReq.Price,
                addBookingObjReq.Quantity,
                u);
        bookingObjectRepo.save(b);
    }
    @Transactional
    public List<BookingObject> getAllBookingObj(){

        return bookingObjectRepo.findAll();
    }
    @Transactional
    public List<BookingObject> getAdsFiltered(getAdsFilteredReq getAdsFilteredReq){
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
        BookingObject Bobj= bookingObjectRepo.findBookingObjectByBid(bid);
        if(DeleteConstraint(bid))
        bookingObjectRepo.delete(Bobj);
    }
    @Transactional
    public List<BookingObject> getMyAds(int uid){
        return bookingObjectRepo.getMyAds(uid);
    }

    @Transactional
    public List<BookingObject> getMyBooked(int uid){

        User user=userRepo.findUserByUid(uid);
        return user.getMyBookings();
    }

    @Transactional
    public String AssignBook(int uid,int bid){
        BookingObject Bobj= bookingObjectRepo.findBookingObjectByBid(bid);
        User user= userRepo.findUserByUid(uid);
        int Quantity=Bobj.getQuantity();
        if(Quantity>0) {
            Bobj.setQuantity(Quantity-1);
        }
        user.getMyBookings().add(Bobj);
        userRepo.save(user);
        bookingObjectRepo.save(Bobj);
        return "Booking is done";
    }

    @Transactional
    public String cancel(int uid,int bid){
        BookingObject Bobj= bookingObjectRepo.findBookingObjectByBid(bid);
        User user= userRepo.findUserByUid(uid);
        int Quantity=Bobj.getQuantity()+1;
        Bobj.setQuantity(Quantity);
        user.getMyBookings().remove(Bobj);
        userRepo.save(user);
        bookingObjectRepo.save(Bobj);
        return "Booking is Cancelled";
    }

    @Transactional
    public boolean CancelConstraint(int bid){
        BookingObject Bobj= bookingObjectRepo.findBookingObjectByBid(bid);
        return Bobj.getPrice() > 100;
    }

    @Transactional
    public boolean DeleteConstraint(int bid){
        BookingObject Bobj= bookingObjectRepo.findBookingObjectByBid(bid);
        return Bobj.getQuantity()==0;
    }

    @Transactional
    public void EditBookingPost(EditBookingObjReq EditBookingObjReq){
        BookingObject BObj= bookingObjectRepo.findBookingObjectByBid(EditBookingObjReq.id);
        BObj.setQuantity(EditBookingObjReq.Quantity);
        BObj.setPrice(EditBookingObjReq.Price);
        BObj.setDescription(EditBookingObjReq.Description);
        bookingObjectRepo.save(BObj);
    }

    @Transactional
    public List<BookingObject> SearchByName(String FindMe){
        FindMe='%'+FindMe+'%';
        return bookingObjectRepo.findBookingObjectFilteredByName(FindMe);
    }

    @Transactional
    public List<BookingObject> SearchByLocation(String FindMe){
        FindMe='%'+FindMe+'%';
        return bookingObjectRepo.findBookingObjectFilteredByLocation(FindMe);
    }
}
