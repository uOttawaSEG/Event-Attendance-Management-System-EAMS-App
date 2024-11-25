package com.example.user_interface_login_page;

import java.util.ArrayList;
import java.util.List;

public class Attendee extends User{
    private List<String> eventIDs; // All events that the attendee registered for (includes pending, accepted and rejected)
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

    // this method is for MainActivity usage
    public void addEventID(String eventID){
        eventIDs.add(eventID);
    }

    public void registerToEvent(String eventID) {
        MainActivity.addEventToAttendee(getUserID(), eventID);
    }

    public  void unregisterToEvent(String eventID) {
        MainActivity.removeEventFromAttendee(this.getUserID(),eventID);
        MainActivity.removeAcceptedAttendeeFromEvent(eventID,this.getUserID());
    }
}


