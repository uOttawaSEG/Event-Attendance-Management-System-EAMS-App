package com.example.user_interface_login_page;

public class Organizer extends User{

    private String organizationName;
    public Organizer(String firstName, String lastName, String emailAddress, String accountPassword, String phoneNumber, String address, String organizationName) throws IllegalArgumentException {
        super(firstName,lastName,emailAddress,accountPassword,phoneNumber,address);

        if (!Validator.validateOrganizationName(organizationName)) {
            throw new IllegalArgumentException("Invalid organization name");
        }

        setUserType("Organizer");
        this.organizationName = organizationName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
