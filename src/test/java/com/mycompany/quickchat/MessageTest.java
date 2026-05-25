package com.mycompany.quickchat;

import com.mycompany.quickchat.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//import org.junit;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 *Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class MessageTest {
    
   
    


  // testing message length if it will be successful

    @Test
    public void testMessageLengthSuccess() {

        Message msg = new Message(
                1,
                "+27831234567",
                "Did you get the cake?"
        );

        boolean result = msg.checkMessageLength();

        assertTrue(result);
    }

    @Test
    public void testMessageLengthFailure() {

        String longMessage =
                "This is a very long message ".repeat(20);

        Message msg = new Message(
                2,
                "+27831234567",
                longMessage
        );

        boolean result = msg.checkMessageLength();

        assertFalse(result);
    }

   
    @Test
    public void testRecipientNumberSuccess() {

        Message msg = new Message(
                1,
                "+27831234567",
                "Hello"
        );

        String expected =
                "Cell phone number successfully captured.";

        String actual = msg.checkRecipientCell();

        assertEquals(expected, actual);
    }

    @Test
    public void testRecipientNumberFailure() {

        Message msg = new Message(
                1,
                "0831234567",
                "Hello"
        );

        String expected =
                "Cell phone number is incorrectly formatted "
                + "or does not contain an international code. "
                + "Please correct the number and try again.";

        String actual = msg.checkRecipientCell();

        assertEquals(expected, actual);
    }

//    @Test
//    public void testMessageLengthSuccess()  {

    @Test
    public void testRecipientCellFailure()  {
        String longMessage =
                "This is a long message that goes on.".repeat(20);

        Message msg =
                new Message(1, "+27718693002", longMessage);

        assertFalse(msg.checkMessageLength());
}
    
    
}

