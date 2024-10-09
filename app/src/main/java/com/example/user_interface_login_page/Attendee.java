package com.example.user_interface_login_page;

public class Attendee extends User{
    public Attendee(String firstName, String lastName, String emailAddress, String accountPassword, String address, String phoneNumber, String organizationName) {
        super(firstName, lastName, emailAddress, accountPassword, address, phoneNumber);
        Validator.validateInputAttendee(firstName, lastName, emailAddress, accountPassword, address, phoneNumber);


    }

}


