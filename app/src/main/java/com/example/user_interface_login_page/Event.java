package com.example.user_interface_login_page;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {
    private String eventTitle;
    private String description;
    private Date eventDate;
    private Time eventStartTime;
    private Time eventEndTime;
    private String eventAddress;
    private boolean autoRegistration;

    private String organizerID;
    private List<String> attendeeIDs;

    private String eventID;

    public Event(String eventTitle, String description, Date eventDate, Time eventStartTime, Time eventEndTime, String eventAddress, boolean autoRegistration, String organizerID) throws IllegalArgumentException {
        if (!Validator.validateDate(eventDate)) {
            throw new IllegalArgumentException("Date has already passed!");
        }
        if (!Validator.compareTimes(eventStartTime, eventEndTime)) {
            throw new IllegalArgumentException("Event end date is before start date!");
        }

        this.eventTitle = eventTitle;
        this.description = description;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventAddress = eventAddress;
        this.autoRegistration = autoRegistration;
        this.organizerID = organizerID;
        this.attendeeIDs = new ArrayList<String>();
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getDescription() {
        return description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public Time getEventStartTime() {
        return eventStartTime;
    }

    public Time getEventEndTime() {
        return eventEndTime;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public boolean getAutoRegistration() {
        return autoRegistration;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventStartTime(Time eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public void setEventEndTime(Time eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public void setAutoRegistration(boolean autoRegistration) {
        this.autoRegistration = autoRegistration;
    }

    public boolean isAutoRegistration() {
        return autoRegistration;
    }

    public String getOrganizerID() {
        return organizerID;
    }

    public void setOrganizerID(String organizerID) {
        this.organizerID = organizerID;
    }

    public List<String> getAttendeeIDs() {
        return attendeeIDs;
    }

    public void setAttendeeIDs(List<String> attendeeIDs) {
        this.attendeeIDs = attendeeIDs;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public void registerAttendee(String attendeeID) {
        this.attendeeIDs.add(attendeeID);
    }
}
