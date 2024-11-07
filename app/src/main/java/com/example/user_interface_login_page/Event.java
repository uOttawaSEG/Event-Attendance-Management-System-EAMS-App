package com.example.user_interface_login_page;

import java.sql.Time;
import java.util.Date;

public class Event {
    private String eventTitle;
    private String description;
    private Date eventDate;
    private Time eventStartTime;
    private Time eventEndTime;
    private String eventAddress;

    public Event(String eventTitle, String description, Date eventDate, Time eventStartTime, Time eventEndTime, String eventAddress) {
        this.eventTitle = eventTitle;
        this.description = description;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventAddress = eventAddress;
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
}
