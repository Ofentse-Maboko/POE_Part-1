/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;

import java.util.Scanner;

public class Quickchat {

    public static void main(String[] args) {

        Scanner user = new Scanner(System.in);

        // Registration section
        System.out.println("\n====== MENU ======");

        System.out.println("Enter Name and Surname:");
        String namesurname = user.nextLine();

        System.out.println("Enter username:");
        String userName = user.nextLine();

        System.out.println("Enter password:");
        String passWord = user.nextLine();

        System.out.println("Enter cellphone number:");
        String cellPhoneNumber = user.nextLine();

        // Call registration method
        String outputmessages = Login2.registeredUser(
                namesurname,
                userName,
                passWord,
                cellPhoneNumber
        );

        // Print returned messages
        System.out.println("\n" + outputmessages);

        // Stop program if registration failed
        if (outputmessages.contains("not correctly formatted")) {

            System.out.println(
                    "Please make sure everything is correct."
            );

            return;
        }

        // Login section
        // Login section
System.out.println("\n====== LOGIN ======");

System.out.println("Enter username:");
String LoginUsername = user.nextLine();

System.out.println("Enter password:");
String LoginPassword = user.nextLine();

if (LoginUsername.equals(userName) && LoginPassword.equals(passWord)) {

    System.out.println("Login successful. Welcome " + namesurname + "!");

    System.out.println("\nEnter recipient number:");
    String recipient = user.nextLine();

    System.out.println("Enter your message:");
    String text = user.nextLine();

    Message msg = new Message (0, recipient, text);

    System.out.println(msg.checkRecipientCell());
    msg.checkMessageLength();

    System.out.println(msg.printMessage());

    String result = msg.sentMessage();
    System.out.println(result);

    if (result.contains("successfully sent")) {
        System.out.println("Total messages sent: " + Message.returnTotalMessages());
    }

} else {
    System.out.println("Username or password incorrect.");
}
}
    }