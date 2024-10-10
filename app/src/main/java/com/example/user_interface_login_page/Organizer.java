package com.example.user_interface_login_page;

public class Organizer extends User{

    private String organizationName;
    public Organizer(String firstName, String lastName, String emailAddress, String accountPassword, String phoneNumber, String address, String organizationName) throws IllegalArgumentException {
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
        setFirstName(firstName);
        setLastName(lastName);
        setEmailAddress(emailAddress);
        setAccountPassword(accountPassword);
        setPhoneNumber(phoneNumber);
        setAddress(address);

        if (!Validator.validateOrganizationName(this.organizationName)) {
            throw new IllegalArgumentException("Invalid organization name");
        }
        this.organizationName = organizationName;
    }
}
