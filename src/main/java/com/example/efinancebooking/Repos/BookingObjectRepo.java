package com.example.efinancebooking.Repos;

import com.example.efinancebooking.Model.BookingObject;
import com.example.efinancebooking.enums.BookingEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookingObjectRepo extends JpaRepository<BookingObject,Integer> {

@Query("select O from BookingObject O where O.id=?1 ")
BookingObject findBookingObjectByBid(int bid) ;

@Query("select O from BookingObject O where O.Publisher.id=?1")
    ArrayList<BookingObject> getMyAds(int uid);
@Query("select O from BookingObject O where O.Price>?1 and O.Price<?2 and O.Quantity>?3")
    List<BookingObject>findBookingObjectFiltered(double minPrice, double maxPrice, int minQuantity);

@Query("select O from BookingEnum O where O.type=?1 ")
BookingEnum findBookingTypeByBid(String type) ;

@Query("select O from BookingObject O where O.Price>?1 and O.Price<?2 and O.Quantity>?3 and O.type.type= ?4")
List<BookingObject>findBookingObjectFilteredWithType(double minPrice, double maxPrice, int minQuantity,String type);
}

