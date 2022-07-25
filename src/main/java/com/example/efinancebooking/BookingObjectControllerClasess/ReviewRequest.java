package com.example.efinancebooking.BookingObjectControllerClasess;

import lombok.Data;

@Data
public class ReviewRequest {
    public int bookingObjId;
    public int Rate;
    public String Comment;
}
