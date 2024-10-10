package com.example.user_interface_login_page;

public class Validator {

    /**
     *
     * @param firstName
     * @param lastName
     * @param emailAddress
     * @param accountPassword
     * @param address
     * @param phoneNumber

     */
    public static String validateInputAttendee(String firstName, String lastName, String emailAddress, String accountPassword, String address, String phoneNumber) {
        String output = "";
        if (!validateFirstName(firstName)){
            output += "Invalid first name, " ;
        }


        if (!validateLastName(lastName)){
            output += "Invalid last name, ";
        }
        if(!validateEmail(emailAddress)){
            output += "Invalid email address, ";
        }
        if (!validatePassword(accountPassword)){
            output += "Invalid password, ";
        };
        if (!validateAddress(address)){
            output += "Invalid address, ";
        };
        if (!validatePhoneNumber(phoneNumber)){
            output += "Invalid phone number.";
        };
        return output;

    }

    /**
     * Validate the first name
     *
     * @return true if it is valid, otherwise false
     */
    public static boolean validateFirstName(String firstName){
        return firstName != null && firstName.matches("[a-zA-Z]+");
    }

    /**
     * Validates the last name
     *
     * @return true if it is valid, otherwise false
     */
    public static boolean validateLastName(String lastName){
        return lastName != null && lastName.matches("[a-zA-Z]+");
    }

    /**
     * Validates email address
     * @
     * @return true if the email address is valid, false otherwise
     */
    public static boolean validateEmail (String emailAddress){
        return emailAddress != null && emailAddress.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    }

    /**
     * Validates the phone number
     *
     * @return true if the phone number is valid, otherwise false
     */
    public static boolean validatePhoneNumber (String phoneNumber){
        return phoneNumber != null && phoneNumber.matches("(\\+\\d{1,3}[- ]?)?\\d{7}");

    }

    /**
     * Validates the Address
     *
     * @return true if the address is valid, otherwise false
     */
    public static boolean validateAddress(String address){
        return address != null && address.matches("[a-zA-Z0-9\\s,.-]+");

    }

    /**
     * Validates the account password
     *
     * @return true if the account password is true, otherwise false
     */
    public static boolean validatePassword(String accountPassword) {
        return accountPassword != null && accountPassword.length()>=8;

    }

    /**
     * validates  the organization Name
     *
     * @return true if the organization is valid, otherwise false
     */
    public static boolean validateOrganizationName(String organizationName) {
        return organizationName != null && organizationName.matches("^[a-zA-Z0-9]+$");
    }


}
