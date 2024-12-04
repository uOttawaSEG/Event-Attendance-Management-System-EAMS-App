package com.example.user_interface_login_page;


import static com.example.user_interface_login_page.Validator.validateDate;

import org.junit.Test;


import static org.junit.Assert.*;

import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testValidateFirstName() {
        assertTrue(Validator.validateFirstName("Elias"));       // Valid name
        assertFalse(Validator.validateFirstName("E"));         // Too short
        assertFalse(Validator.validateFirstName(""));          // Empty name
        assertFalse(Validator.validateFirstName("Anthonyiscool1234"));   // Invalid name (contains numbers)
    }

    @Test
    public void testtValidateLastName() {
        assertTrue(Validator.validateLastName("Coumine"));       // Valid name
        assertFalse(Validator.validateLastName("C"));         // Too short
        assertFalse(Validator.validateLastName(""));          // Empty name

    }

    @Test
    public void testValidateEmail() {
        assertTrue(Validator.validateEmail("RodricTate@example.com"));   // Valid email
        assertFalse(Validator.validateEmail("RodricTate"));     // Invalid email
        assertFalse(Validator.validateEmail(""));                  // Empty email
    }

    // Test validatePhoneNumber
    @Test
    public void testValidatePhoneNumber() {
        assertTrue(Validator.validatePhoneNumber("+123456789"));    // Valid phone number
        assertFalse(Validator.validatePhoneNumber("1234"));           // Invalid phone number
        assertFalse(Validator.validatePhoneNumber(""));              // Empty phone number
    }


    // Test validatePassword
    @Test
    public void testValidatePassword() {
        assertTrue(Validator.validatePassword("Password123"));    // Valid password
        assertFalse(Validator.validatePassword("short"));          // Too short password
        assertFalse(Validator.validatePassword(""));               // Empty password
    }

    @Test
    public void testValidateEventTitle() {
        assertTrue(Validator.validateEventTitle("Event Title"));      // Valid event title
        assertFalse(Validator.validateEventTitle(""));                // Empty event title
    }

    // Test validateEventDescription
    @Test
    public void testValidateEventDescription() {
        assertTrue(Validator.validateEventDescription("This is a description of the event."));   // Valid event description
        assertFalse(Validator.validateEventDescription(""));                                                // Empty description
    }

    @Test
    public void testValidateDate() {
        Long currentDate = new Date().getTime(); // Current date in milliseconds
        Long futureDate = currentDate + 10000L;  // 10 seconds later
        Long pastDate = currentDate - 10000L;    // 10 seconds before


        assertTrue("Date should be in the future", validateDate(futureDate));  // Test with a future date (should return true)


        assertFalse("Date should not be in the past", validateDate(pastDate)); // Test with a past date (should return false)


        assertFalse("Date should not be the current date", validateDate(currentDate));// Test with the current date (should return false)
    }








}