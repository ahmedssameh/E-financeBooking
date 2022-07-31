package com.example.efinancebooking.Repos;

import com.example.efinancebooking.Model.Booking;
import com.example.efinancebooking.Model.BookingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {

@Query("select O from Booking O where O.id=?1 ")
Booking findBookingObjectByBid(int bid) ;

@Query("select O from Booking O where O.Publisher.id=?1")
    ArrayList<Booking> getPublisherBookings(int uid);

@Query("select O from Booking O where O.Price>?1 and O.Price<?2 and O.Quantity>?3")
    List<Booking>findBookingObjectFiltered(double minPrice, double maxPrice, int minQuantity);

@Query("select O from BookingType O where O.type=?1 ")
BookingType findBookingTypeByName(String type) ;

@Query("select O from Booking O where O.Price>?1 and O.Price<?2 and O.Quantity>?3 and O.type.type= ?4")
List<Booking>findBookingObjectFilteredWithType(double minPrice, double maxPrice, int minQuantity, String type);

@Query("select O from Booking O where O.Name like ?1")
List<Booking>findBookingObjectFilteredByName(String FindMe);


@Query("select O from Booking O where O.Location like ?1")
List<Booking>findBookingObjectFilteredByLocation(String FindMe);

@Query("select O from Booking O where O.status ='active'")
List<Booking>findActiveBookingObject();

}

