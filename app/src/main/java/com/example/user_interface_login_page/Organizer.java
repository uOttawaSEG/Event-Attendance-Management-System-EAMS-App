package com.example.user_interface_login_page;

public class Organizer extends User{

    private String nameOfOrganization;
    public Organizer(String firstName, String lastName, String emailAddress, String accountPassword, String address, String phoneNumber, String nameOfOrganisation) {
        super(firstName, lastName, emailAddress, accountPassword, address, phoneNumber);
        this.nameOfOrganization = nameOfOrganisation;
    }
}
