package com.example.user_interface_login_page;

import java.sql.Time;
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
    private List<Attendee> listOfAttendees;

    public Event(String eventTitle, String description, Date eventDate, Time eventStartTime, Time eventEndTime, String eventAddress, boolean autoRegistration, List<Attendee> listOfAttendees) throws Exception {
        if (!Validator.validateDate(eventDate)) {
            throw new Exception("Date has already passed!");
        }
        if (!Validator.compareTimes(eventStartTime, eventEndTime)) {
            throw new Exception("Event end date is before start date!");
        }

        this.eventTitle = eventTitle;
        this.description = description;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventAddress = eventAddress;
        this.autoRegistration = autoRegistration;
        this.listOfAttendees = listOfAttendees;
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

    public List<Attendee> getListOfAttendees() {
        return listOfAttendees;
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

    public void setListOfAttendees(List<Attendee> listOfAttendees) {
        this.listOfAttendees = listOfAttendees;
    }

    public void registerAttendee(Attendee attendee) {
        this.listOfAttendees.add(attendee);
    }
}
