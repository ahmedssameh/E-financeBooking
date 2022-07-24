package com.example.efinancebooking.UserRequests;

import lombok.Data;

@Data
public class AddUserRequest {
    private String Username;
    private String Password;
    private String Email;
    private double PhoneNumber;

}
