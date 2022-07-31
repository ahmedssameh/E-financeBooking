package com.example.efinancebooking.BookingObjectControllerClasess;

import lombok.Data;

@Data
public class ReviewRequest {
    public int bookingObjId;
    public int rate;
    public String comment;
}
