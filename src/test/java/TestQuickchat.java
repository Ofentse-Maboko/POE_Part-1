/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.quickchat.Login2;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;



//@Test
public class TestQuickchat {
    

    

    @Test
    void testPasswordTooShort() {
        assertFalse(Login2.checkPasswordComplexity("Ch@1"));
    }

    @Test
    void testPasswordNoUppercase() {
        assertFalse(Login2.checkPasswordComplexity("ch&&sec@ke99!"));
    }
  
    Login2 login = new Login2();

    // Username must contain "_" and be <= 5 characters
    @Test
    void testInvalidUsername() {
        boolean result = login.checkUserName("abcde");
        assertFalse(result, "Username is not correctly formatted");
    }

    // Password meets complexity requirements
    @Test
    void testValidPassword() {
        boolean result = login.checkPasswordComplexity("Ch&&sec@ke99!");
        assertTrue(result, "Password successfully captured.");
    }

    // Password does NOT meet complexity requirements
    

    // Valid cell phone number
    @Test
    void testValidCellNumber() {
        boolean result = login.checkCellPhoneNumber("+27838968976");
        assertTrue(result, "Cell number successfully captured.");
    }

    // Checks to see if Invalid cell phone number
   // Cell phone incorrectly formatted
    @Test
    void testInvalidCellPhone() {
        boolean result = login.checkCellPhoneNumber("08966553");
        assertFalse(result, "Cell number is incorrectly formatted or does not contain an international code.");
    }

    // Login successful
    @Test
    void testLoginSuccessful() {
        boolean result = login.LoginUser("kyl_1", "Ch&&sec@ke99!");
        assertFalse(result,"Login successful");
    }

    //checks to see if Login failed
    @Test
    void testLoginFailed() {
        boolean result = login.LoginUser("wrongUser", "password");
        assertFalse(result,"Login Failed");
    }

    // checks if is Username correctly formatted
    @Test
    void testValidUsername() {
        boolean result = login.checkUserName("kyl_1");
        assertTrue(result);
    }
    
     // Username incorrectly formatted
    
    
    // Password does NOT meet complexity requirements
    @Test
    void testInvalidPassword() {
        boolean result = login.checkPasswordComplexity("password");
        assertFalse(result,"Password does not meet complexity requirements");
    }

    //checks if is Cell phone correctly formatted
    @Test
    void testValidCellPhone() {
        boolean result = login.checkCellPhoneNumber("+27838968976");
        assertTrue(result,"Cellphone number correctly formatted");
    }

    
}
    
//}


