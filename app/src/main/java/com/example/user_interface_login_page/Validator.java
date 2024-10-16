package com.example.user_interface_login_page;

public class Validator {

    private static final String FIRSTNAME_PATERN = "[a-zA-Z]+";
    private static final String LASTNAME_PATERN = "[a-zA-Z]+";
    private static final String EMAIL_ADDRESS_PATERN = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    private static final String ACCOUNT_PASSWORD_PATERN = "^.{8,}$";
    private static final String PHONENUMBER_PATTERN = "^\\+?[0-9. ()-]{7,15}$";
    private static final String ADDRESS_PATTERN = "^\\d+\\s[A-z]+(?:\\s[A-z]+)*(?:\\s(?:Apt|Unit|Suite)\\s\\d+)?(?:,\\s[A-z]+)*(?:,\\s[A-Z]{2})?(?:\\s\\d{5})?$";
    private static final String ORGANIZATIONNAME_PATERN = "^[a-zA-Z0-9]+$";

    /**
     * Validate the first name
     *
     * @return true if it is valid, otherwise false
     */
    public static boolean validateFirstName(String firstName){
        return !firstName.isEmpty() && firstName.length() >= 2 && firstName.matches(FIRSTNAME_PATERN);
    }

    /**
     * Validates the last name
     *
     * @return true if it is valid, otherwise false
     */
    public static boolean validateLastName(String lastName){
        return !lastName.isEmpty() && lastName.length() >= 2 && lastName.matches(LASTNAME_PATERN);
    }

    /**
     * Validates email address
     * @
     * @return true if the email address is valid, false otherwise
     */
    public static boolean validateEmail (String emailAddress){
        return !emailAddress.isEmpty() && emailAddress.matches(EMAIL_ADDRESS_PATERN);

    }

    /**
     * Validates the phone number
     *
     * @return true if the phone number is valid, otherwise false
     */
    public static boolean validatePhoneNumber (String phoneNumber){
        return !phoneNumber.isEmpty() && phoneNumber.matches(PHONENUMBER_PATTERN);
    }

    /**
     * Validates the Address
     *
     * @return true if the address is valid, otherwise false
     */
    public static boolean validateAddress(String address){
        return !address.isEmpty() && address.matches(ADDRESS_PATTERN);
    }

    /**
     * Validates the account password
     *
     * @return true if the account password is true, otherwise false
     */
    public static boolean validatePassword(String accountPassword) {
        return !accountPassword.isEmpty() && accountPassword.length()>=8 && accountPassword.matches(ACCOUNT_PASSWORD_PATERN);

    }

    /**
     * validates  the organization Name
     *
     * @return true if the organization is valid, otherwise false
     */
    public static boolean validateOrganizationName(String organizationName) {
        return !organizationName.isEmpty() && organizationName.matches(ORGANIZATIONNAME_PATERN);
    }


}
