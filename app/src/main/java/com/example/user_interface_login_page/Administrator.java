package com.example.user_interface_login_page;

public class Administrator extends User {
    public Administrator(String firstName, String lastName, String emailAddress, String accountPassword, String address, String phoneNumber) {
        super(firstName, lastName, emailAddress, accountPassword, address, phoneNumber);
        setUserType("Administrator");
    }
}
