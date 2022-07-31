package com.example.efinancebooking.BookingRequests;

import lombok.Data;

@Data
public class EditBookingReq {

    public int id;
    public String Description;
    public int Quantity;
    public double Price;
}
