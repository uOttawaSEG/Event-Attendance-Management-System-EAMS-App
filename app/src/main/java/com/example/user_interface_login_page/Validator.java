package com.example.user_interface_login_page;

public class Validator {

    private static final String ADDRESS_PATTERN = "^\\d+\\s[A-z]+(?:\\s[A-z]+)*(?:\\s(?:Apt|Unit|Suite)\\s\\d+)?(?:,\\s[A-z]+)*(?:,\\s[A-Z]{2})?(?:\\s\\d{5})?$";
    private static final String PHONE_PATTERN = "^\\+?[0-9. ()-]{7,15}$";

    /**
     * Validate the first name
     *
     * @return true if it is valid, otherwise false
     */
    public static boolean validateFirstName(String firstName){
        return !firstName.isEmpty() && firstName.length() >= 2 && firstName.matches("[a-zA-Z]+");
    }

    /**
     * Validates the last name
     *
     * @return true if it is valid, otherwise false
     */
    public static boolean validateLastName(String lastName){
        return !lastName.isEmpty() && lastName.length() >= 2 && lastName.matches("[a-zA-Z]+");
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
        return !phoneNumber.isEmpty() && phoneNumber.matches(PHONE_PATTERN);
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
        return !address.isEmpty() && address.matches(ADDRESS_PATTERN);

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
