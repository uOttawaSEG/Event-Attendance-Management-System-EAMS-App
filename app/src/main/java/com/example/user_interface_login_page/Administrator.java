package com.example.user_interface_login_page;

public class Administrator extends User {
    public Administrator(String firstName, String lastName, String emailAddress, String accountPassword, String phoneNumber, String address) {
        super(firstName, lastName, emailAddress, accountPassword, phoneNumber, address);
        setUserType("Administrator");
    }
}
