package com.example.user_interface_login_page;

public abstract class Person {

    /**
     * Initializing all the instances variables associated to Attendees, Organizers and Administrators
     */
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String accountPassword;

    private String address;

    private String phoneNumber;

    /**
     * Constructor to be called from sub-classes
     * @param firstName, first name of Person
     * @param lastName, last name of Person
     * @param emailAddress, email address of Person
     * @param accountPassword, account password of Person
     * @param address, address of Person
     * @param phoneNumber, phone number of Person
     */
    public Person(String firstName, String lastName, String emailAddress, String accountPassword, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.accountPassword = accountPassword;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //Getters and setters associated to all fields of Person class
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
