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
     * @param organizationName

     */

    /**
     * Validate the first name
     *
     * @return true if it is valid, otherwise false
     */
    public static boolean validateFirstName(String firstName){
        return !firstName.isEmpty() && firstName.matches("[a-zA-Z]+");
    }

    /**
     * Validates the last name
     *
     * @return true if it is valid, otherwise false
     */
    public static boolean validateLastName(String lastName){
        return !lastName.isEmpty() && lastName.matches("[a-zA-Z]+");
    }

    /**
     * Validates email address
     * @
     * @return true if the email address is valid, false otherwise
     */
    public static boolean validateEmail (String emailAddress){
        return !emailAddress.isEmpty() && emailAddress.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    }

    /**
     * Validates the phone number
     *
     * @return true if the phone number is valid, otherwise false
     */
    public static boolean validatePhoneNumber (String phoneNumber){
        return !phoneNumber.isEmpty() && phoneNumber.matches("(^+?\\d{1,3}[- ]?)?(\\d{3}[- ]?\\d{3}[- ]?\\d{4})$");
    }

    /**
     * Validates the Address
     *
     * @return true if the address is valid, otherwise false
     */
    public static boolean validateAddress(String address){
//        boolean foundLetter = false;
//        boolean foundNumber = false;
//        for (int i=0;i<address.length();i++) {
//            if (Character.isLetter(address.charAt(i))) {
//                foundLetter = true;
//            }
//            if (Character.isDigit(address.charAt(i))) {
//                foundNumber = true;
//            }
//        }
//
//        return !address.isEmpty() && foundNumber && foundLetter;
        return !address.isEmpty() && address.matches("[a-zA-Z0-9\\s,.-]+");

    }

    /**
     * Validates the account password
     *
     * @return true if the account password is true, otherwise false
     */
    public static boolean validatePassword(String accountPassword) {
        return !accountPassword.isEmpty() && accountPassword.length()>=8;

    }

    /**
     * validates  the organization Name
     *
     * @return true if the organization is valid, otherwise false
     */
    public static boolean validateOrganizationName(String organizationName) {
        return !organizationName.isEmpty() && organizationName.matches("^[a-zA-Z0-9]+$");
    }


}
