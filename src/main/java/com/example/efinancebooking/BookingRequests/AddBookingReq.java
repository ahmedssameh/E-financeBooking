package com.example.efinancebooking.BookingRequests;


import lombok.Data;

@Data
public class AddBookingReq {
    public String Name;
    public String Location;
//    public int userid;
    public String type;
    public java.sql.Date PublishedDate;
    public String Description;
    public double Price;
    public int Quantity;


}
