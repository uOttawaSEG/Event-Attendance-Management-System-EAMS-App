package com.example.user_interface_login_page;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private String eventTitle;
    private String description;
    private long eventDateMillis;
    private long eventStartTimeMillis;
    private long eventEndTimeMillis;
    private String eventAddress;
    private boolean autoRegistration;

    private String organizerID;
    private List<String> pendingAttendeeIDs;
    private List<String> acceptedAttendeeIDs;

    private String eventID;

    public Event(String eventTitle, String description, long eventDateMillis, long eventStartTimeMillis, long eventEndTimeMillis, String eventAddress, boolean autoRegistration, String organizerID) throws IllegalArgumentException {
        if(!Validator.validateEventTitle(eventTitle)) {
            throw new IllegalArgumentException("Event must have a title, composed of 1 or more characters!");
        }
        if (!Validator.validateEventDescription(description)) {
            throw new IllegalArgumentException("Event must have a description, composed of 1 or more characters!");
        }
        if (!Validator.validateDate(eventStartTimeMillis)) {
            throw new IllegalArgumentException("Date has already passed (cannot be today) or has been left blank!");
        }
        if (!Validator.compareTimes(eventStartTimeMillis, eventEndTimeMillis)) {
            throw new IllegalArgumentException("Event end date is before start date or one of the fields are blank!");
        }
        if (!Validator.validateEventAddress(eventAddress)) {
            throw new IllegalArgumentException("Address must match this form: <Unit Number> <Apt.|Unit|Suite> <Street>");
        }

        this.eventTitle = eventTitle;
        this.description = description;
        this.eventDateMillis = eventDateMillis;
        this.eventStartTimeMillis = eventStartTimeMillis;
        this.eventEndTimeMillis = eventEndTimeMillis;
        this.eventAddress = eventAddress;
        this.autoRegistration = autoRegistration;
        this.organizerID = organizerID;
        this.pendingAttendeeIDs = new ArrayList<String>();
        this.acceptedAttendeeIDs = new ArrayList<String>();
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getEventDateMillis() {
        return eventDateMillis;
    }

    public void setEventDateMillis(long eventDateMillis) {
        this.eventDateMillis = eventDateMillis;
    }

    public long getEventStartTimeMillis() {
        return eventStartTimeMillis;
    }

    public void setEventStartTimeMillis(long eventStartTimeMillis) {
        this.eventStartTimeMillis = eventStartTimeMillis;
    }

    public long getEventEndTimeMillis() {
        return eventEndTimeMillis;
    }

    public void setEventEndTimeMillis(long eventEndTimeMillis) {
        this.eventEndTimeMillis = eventEndTimeMillis;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public boolean isAutoRegistration() {
        return autoRegistration;
    }

    public void setAutoRegistration(boolean autoRegistration) {
        this.autoRegistration = autoRegistration;
    }

    public String getOrganizerID() {
        return organizerID;
    }

    public void setOrganizerID(String organizerID) {
        this.organizerID = organizerID;
    }

    public List<String> getPendingAttendeeIDs() {
        return pendingAttendeeIDs;
    }

    public void setPendingAttendeeIDs(List<String> pendingAttendeeIDs) {
        this.pendingAttendeeIDs = pendingAttendeeIDs;
    }

    public List<String> getAcceptedAttendeeIDs() {
        return acceptedAttendeeIDs;
    }

    public void setAcceptedAttendeeIDs(List<String> acceptedAttendeeIDs) {
        this.acceptedAttendeeIDs = acceptedAttendeeIDs;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public void acceptAttendee(String attendeeID){
        pendingAttendeeIDs.remove(attendeeID);
        acceptedAttendeeIDs.add(attendeeID);
    }

    public void rejectAttendee(String attendeeID){
        pendingAttendeeIDs.remove(attendeeID);
    }

    public void addPendingAttendeeID(String ID) {
        pendingAttendeeIDs.add(ID);
    }
    public void addAcceptedAttendeeID(String ID) {
        acceptedAttendeeIDs.add(ID);
    }

    @NonNull
    public String toString() {
        return this.getEventTitle();
    }
}
