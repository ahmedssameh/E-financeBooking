package com.example.efinancebooking.BookingObjectControllerClasess;


import lombok.Data;

@Data
public class addBookingObjReq {
    public String Name;
    public String Location;
    public int userid;
    public String type;
    public java.sql.Date PublishedDate;
    public String Description;
    public double Price;
    public int Quantity;

}
