/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;

import java.util.Scanner;


public class Quickchat {

    //main method to run the code in the console
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
        System.out.println("\n====== LOGIN ======");

        System.out.println("Enter username:");
        String LoginUsername = user.nextLine();

        System.out.println("Enter password:");
        String LoginPassword = user.nextLine();

        if (LoginUsername.equals(userName)
                && LoginPassword.equals(passWord)) {

            System.out.println(
                    "Login successful. Welcome "
                    + namesurname + "!"
            );

            // Welcome message
            System.out.println("\nWelcome to QuickChat.");

            // Ask how many messages user wants to enter
            System.out.println(
                    "\nHow many messages would you like to enter?"
            );

            int maxMessages = user.nextInt();
            user.nextLine();

            int option;
            int messagesSent = 0;

            do {

                // Main menu
                System.out.println("\n====== QUICKCHAT MENU ======");
                System.out.println("1. Send Messages");
                System.out.println(
                        "2. Show recently sent messages"
                );
                System.out.println("3. Quit");

                System.out.println("Choose option:");
                option = user.nextInt();
                user.nextLine();

                switch (option) {

                    case 1:

                        // Limit number of messages
                        if (messagesSent >= maxMessages) {

                            System.out.println(
                                    "You have reached "
                                    + "your message limit."
                            );

                            break;
                        }

                        // Capture message
                        System.out.println(
                                "\nEnter recipient number:"
                        );

                        String recipient = user.nextLine();

                        System.out.println(
                                "Enter your message:"
                        );

                        String text = user.nextLine();

                        // Create message object
                        Message msg = new Message(
                                messagesSent + 1,
                                recipient,
                                text
                        );

                        // Validate recipient
                        System.out.println(
                                msg.checkRecipientCell()
                        );

                        // Validate message length
                        if (msg.checkMessageLength()) {

                            // Print message details
                            System.out.println(
                                    msg.printMessage()
                            );

                            // Send/store/delete message
                            String result = msg.sentMessage();

                            System.out.println(result);

                            // Count sent messages
                            if (result.contains(
                                    "successfully sent")) {

                                messagesSent++;

                                System.out.println(
                                        "Total messages sent: "
                                        + Message.returnTotalMessages()
                                );
                            }
                        }

                        break;

                    case 2:

                        System.out.println("Coming Soon.");

                        break;

                    case 3:

                        System.out.println(
                                "Thank you for using QuickChat."
                        );

                        break;

                    default:

                        System.out.println(
                                "Invalid option."
                        );
                }

            } while (option != 3);

        } else {

            System.out.println(
                    "Username or password incorrect."
            );
        }
    }
}