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
    private int userID;


    /**
     * Unparameterized constructor for User class
     */
    public User() {
        this.firstName = "";
        this.lastName = "";
        this.emailAddress = "";
        this.accountPassword = "";
        this.phoneNumber = "";
        this.address = "";
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
            throw new IllegalArgumentException("Invalid first name");
        }
        if (!Validator.validateLastName(lastName)){
            throw new IllegalArgumentException("Invalid last name");
        }
        if(!Validator.validateEmail(emailAddress)){
            throw new IllegalArgumentException("Invalid email address");
        }
        if (!Validator.validatePassword(accountPassword)){
            throw new IllegalArgumentException("Invalid password");
        }
        if (!Validator.validatePhoneNumber(phoneNumber)){
            throw new IllegalArgumentException("Invalid phone number.");
        }
        if (!Validator.validateAddress(address)){
            throw new IllegalArgumentException("Invalid address");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.accountPassword = accountPassword;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public abstract String getUserType();
}
