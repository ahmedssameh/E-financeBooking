package com.example.efinancebooking.BookingObjectControllerClasess;

import lombok.Data;

@Data
public class EditBookingObjReq {

    public int id;
    public String Description;
    public int Quantity;
    public double Price;
}
