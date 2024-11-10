package com.example.user_interface_login_page;

import android.app.TimePickerDialog;

import java.sql.Time;
import java.util.Date;

public class Validator {

    //Regex validation for attendees and organizers
    private static final String FIRSTNAME_PATTERN = "[a-zA-Z]+";
    private static final String LASTNAME_PATTERN = "[a-zA-Z]+";
    private static final String EMAIL_ADDRESS_PATTERN = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    private static final String ACCOUNT_PASSWORD_PATTERN = "^.{8,}$";
    private static final String PHONENUMBER_PATTERN = "^\\+?[0-9. ()-]{7,15}$";
    private static final String ADDRESS_PATTERN = "^\\d+\\s[A-z]+(?:\\s[A-z]+)*(?:\\s(?:Apt|Unit|Suite)\\s\\d+)?(?:,\\s[A-z]+)*(?:,\\s[A-Z]{2})?(?:\\s\\d{5})?$";
    private static final String ORGANIZATIONNAME_PATTERN = "^.+$";

    //Regex validation for events
    private static final String EVENT_ADDRESS_PATTERN = ADDRESS_PATTERN;
    private static final String DESCRIPTION_PATTERN = ORGANIZATIONNAME_PATTERN;
    private static final String EVENT_TITLE_PATTERN = ORGANIZATIONNAME_PATTERN;

    /**
     * Validate the first name
     *
     * @return true if it is valid, otherwise false
     */
    public static boolean validateFirstName(String firstName){
        return !firstName.isEmpty() && firstName.length() >= 2 && firstName.matches(FIRSTNAME_PATTERN);
    }

    /**
     * Validates the last name
     *
     * @return true if it is valid, otherwise false
     */
    public static boolean validateLastName(String lastName){
        return !lastName.isEmpty() && lastName.length() >= 2 && lastName.matches(LASTNAME_PATTERN);
    }

    /**
     * Validates email address
     * @
     * @return true if the email address is valid, false otherwise
     */
    public static boolean validateEmail (String emailAddress){
        return !emailAddress.isEmpty() && emailAddress.matches(EMAIL_ADDRESS_PATTERN);

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
        return !accountPassword.isEmpty() && accountPassword.length()>=8 && accountPassword.matches(ACCOUNT_PASSWORD_PATTERN);

    }

    /**
     * validates the organization name
     *
     * @return true if the organization name is valid, otherwise false
     */
    public static boolean validateOrganizationName(String organizationName) {
        return !organizationName.isEmpty() && organizationName.matches(ORGANIZATIONNAME_PATTERN);
    }

    /**
     * validates the fact that startTime occurs before endTime
     *
     * @return true if this condition is true, otherwise false
     */
    public static boolean compareTimes(Long startTime, Long endTime) {
        return startTime<endTime;
    }

    /**
     * validates the fact that enteredDate hasn't passed yet
     *
     * @return true if this condition is true, otherwise false
     */
    public static boolean validateDate(Long enteredDate) {
        Long currentDate = new Date().getTime();
        return enteredDate > currentDate;
    }

    /**
     * validates the event address
     *
     * @return true if the event address is valid, otherwise false
     */
    public static boolean validateEventAddress(String providedEventAddress) {
        return !providedEventAddress.isEmpty() && providedEventAddress.matches(EVENT_ADDRESS_PATTERN);
    }

    /**
     * validates the event title
     *
     * @return true if the event title is valid, otherwise false
     */
    public static boolean validateEventTitle(String providedEventTitle) {
        return !providedEventTitle.isEmpty() && providedEventTitle.matches(EVENT_TITLE_PATTERN);
    }

    /**
     * validates the event description
     *
     * @return true if the event description is valid, otherwise false
     */
    public static boolean validateEventDescription(String providedEventDescription) {
        return !providedEventDescription.isEmpty() && providedEventDescription.matches(DESCRIPTION_PATTERN);
    }
}
