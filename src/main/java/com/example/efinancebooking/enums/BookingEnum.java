package com.example.efinancebooking.enums;

public enum BookingEnum {
    Hotels("Hotels"),
    TravellingTicket("TravellingTicket"),
    Cars("Cars");
    final String type;

    BookingEnum(String type) {
        this.type = type;
    }
}
