package com.example.user_interface_login_page;

import java.util.ArrayList;

public class Organizer extends User{

    private String organizationName;
    private ArrayList<String> eventIDs;

    public Organizer(String firstName, String lastName, String emailAddress, String accountPassword, String phoneNumber, String address, String organizationName) throws IllegalArgumentException {
        super(firstName,lastName,emailAddress,accountPassword,phoneNumber,address);

        if (!Validator.validateOrganizationName(organizationName)) {
            throw new IllegalArgumentException("Organization Name must have at least 1 character.");
        }

        setUserType("Organizer");
        this.organizationName = organizationName;
        this.eventIDs = eventIDs;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public ArrayList<String> getEventIDs() {
        return eventIDs;
    }

    public void setEventIDs(ArrayList<String> eventIDs) {
        this.eventIDs = eventIDs;
    }
}
