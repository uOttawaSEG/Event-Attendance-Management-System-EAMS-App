package com.example.user_interface_login_page;

import java.util.ArrayList;
import java.util.List;

public class Attendee extends User{
    private List<String> eventIDs;
    public Attendee(String firstName, String lastName, String emailAddress, String accountPassword, String phoneNumber, String address) {
        super(firstName, lastName, emailAddress, accountPassword, phoneNumber, address);
        eventIDs = new ArrayList<String>();
        setUserType("Attendee");
    }

    public List<String> getEventIDs() {
        return eventIDs;
    }

    public void setEventIDs(List<String> eventIDs) {
        this.eventIDs = eventIDs;
    }

    public void addEventIDs(String string){
        eventIDs.add(string);
    }
}


