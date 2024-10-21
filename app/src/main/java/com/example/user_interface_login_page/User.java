package com.example.user_interface_login_page;

public abstract class User {

    /**
     * Initializing all the instances variables associated to Attendees, Organizers and Administrators
     */
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String accountPassword;
    private String phoneNumber;
    private String address;
    private String userID;
    private String userType;
    private String registrationStatus;

    public User() {
        this.firstName = "";
        this.lastName = "";
        this.emailAddress = "";
        this.accountPassword = "";
        this.phoneNumber = "";
        this.address = "";
        this.userType = "N/A";
        this.registrationStatus = "Pending";
    }

    /**
     * Constructor to be called from sub-classes
     * @param firstName, first name of Person
     * @param lastName, last name of Person
     * @param emailAddress, email address of Person
     * @param accountPassword, account password of Person
     * @param address, address of Person
     * @param phoneNumber, phone number of Person
     */
    public User(String firstName, String lastName, String emailAddress, String accountPassword, String phoneNumber, String address) throws IllegalArgumentException {
        if (!Validator.validateFirstName(firstName)){
            throw new IllegalArgumentException("First Name needs to be composed of at least two alphabetic characters.");
        }
        if (!Validator.validateLastName(lastName)){
            throw new IllegalArgumentException("Last Name needs to be composed of at least two alphabetic characters.");
        }
        if(!Validator.validateEmail(emailAddress)){
            throw new IllegalArgumentException("Email Address needs to match this form: EXAMPLE@WEBSITE.END");
        }
        if (!Validator.validatePassword(accountPassword)){
            throw new IllegalArgumentException("Account Password needs to be at least 8 characters.");
        }
        if (!Validator.validatePhoneNumber(phoneNumber)){
            throw new IllegalArgumentException("Phone Number needs at least 7 numbers with optional area code.");
        }
        if (!Validator.validateAddress(address)){
            throw new IllegalArgumentException("Address must match this form: <Unit Number> <Apt.|Unit|Suite> <Street>");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.accountPassword = accountPassword;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userType = "N/A";
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
