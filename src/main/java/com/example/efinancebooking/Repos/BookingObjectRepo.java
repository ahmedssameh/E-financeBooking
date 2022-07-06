package com.example.efinancebooking.Repos;

import com.example.efinancebooking.Model.BookingObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookingObjectRepo extends JpaRepository<BookingObject,Integer> {

@Query("select O from BookingObject O where O.bid=?1 ")
BookingObject findBookingObjectByBid(int bid) ;

@Query("select O from BookingObject O where O.Publisher.uid=?1")
    ArrayList<BookingObject> getMyAds(int uid);
}
