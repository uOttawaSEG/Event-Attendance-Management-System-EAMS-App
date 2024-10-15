package com.example.user_interface_login_page;

public class Attendee extends User{
    public Attendee(String firstName, String lastName, String emailAddress, String accountPassword, String phoneNumber, String address) {
        super(firstName, lastName, emailAddress, accountPassword, phoneNumber, address);
        setUserType("Attendee");
    }
}


