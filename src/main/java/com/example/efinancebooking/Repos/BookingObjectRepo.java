package com.example.efinancebooking.Repos;

import com.example.efinancebooking.Model.BookingObject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingObjectRepo extends CrudRepository<BookingObject,Integer> {
@Query("select O from BookingObject O where O.bid=?1 "  )
public BookingObject findBookingObjectByBid(int bid) ;
}
