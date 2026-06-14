/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

//import static java.util.stream.Gatherers.scan;

public class Message {

    //THese are propeties of message

    private String messageID;
    private int MessageNumber;
    private String recipient;
    private String MessageText;
    private String MessagesHash;
    private static int totalMessagesSent = 0;

    //these are the arrays mentioned in Part3
    private static String[] sentMessages = new String[100];
    private static String[] disregardedMessages = new String[100];
    private static String[] storedMessages = new String[100];

    //parallel arrays
    private static String[] recipients = new String[100];
    private static String[] messageHashes = new String[100];
    private static String[] messageIDs = new String[100];

    private static int sentCount = 0;
    private static int disregardedCount = 0;
    private static int storedCount = 0;

    //this runs when u make new message

    public Message(int messageNumber, String recipient, String messageText) {

        this.MessageNumber = messageNumber;
        this.recipient = recipient;
        this.MessageText = messageText;

        this.messageID = generateMessageID();
        this.MessagesHash = createMessageHash();

    }

    //method for checking the length of the message
    public boolean checkMessageLength() {

        if (MessageText.length() <= 250) {

            System.out.println("message ready to send");
            return true;
        } else {

            int excess = MessageText.length() - 250;

            System.out.println(
                    "message exceeds 250 characters by "
                    + excess
                    + "; please reduce the size"
            );

            return false;
        }

    }

    //method for verifiying num
    public String checkRecipientCell() {

        if (recipient.matches("\\+27\\d{9}")) {

            String numbers = recipient.substring(3);

            if (numbers.matches("\\d+")) {

                return "cellphone number successfully captured.";
            }

        }

        return "cellphone number is incorrectly formatted or does not cantain captured.";

    }

    //method for hasing message
    public String createMessageHash() {

        if (messageID == null || MessageText == null) {
            return "INVALID_HASH";
        }

        String firstTwo = messageID.length() >= 2
                ? messageID.substring(0, 2)
                : messageID;

        String msgNum = String.valueOf(MessageNumber);

        String[] words = MessageText.split(" ");

        if (words.length == 0 || words[0].isEmpty()) {

            return firstTwo + ":" + msgNum + ":EMPTY";
        }

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return (
                firstTwo
                + ":" + msgNum
                + ":" + firstWord
                + ":" + lastWord
        ).toUpperCase();
    }

    //generate the ids using ranform
    private String generateMessageID() {

        Random rand = new Random();

        long id =
                1000000000L
                + (long) (rand.nextDouble() * 9000000000L);

        return String.valueOf(id);

    }

    //methoos for sending messages
    public String sentMessage() {

        Scanner Scan = new Scanner(System.in);

        boolean isValidPhone =
                recipient.matches("\\+27\\d{9}");

        System.out.println(" \nWhat would you like to do:");
        System.out.println(" 1.Send Message");
        System.out.println("0.Disregard Message(Delete)");
        System.out.println("3.Store messages to send later");
        System.out.println("Choose options(1/0/3): ");

        int choice = Scan.nextInt();

        if (choice == 1) {

            if (isValidPhone) {

                totalMessagesSent++;

                addSentMessage();
                addStoredMessage();

                return "Message successfully sent";

            } else {

                return "Cannot send messages: Invalid phone number! please use +27 followed by 9 digits";
            }

        } else if (choice == 0) {

            addDisregardedMessage();

            return "Message deleted.";

        } else if (choice == 3) {

            addStoredMessage();

            storeMessage();

            return "Message successfully stored.";

        } else {

            return "invalid option";
        }
    }

    //method for storing in json
    public void storeMessage() {

        try {

            String jsonContent = "{\n";
            jsonContent += "\"messageID\": \"" + messageID + "\",\n";
            jsonContent += "\"messageNumber\": \"" + MessageNumber + "\",\n";
            jsonContent += "\"recipient\": \"" + recipient + "\",\n";
            jsonContent += "\"messageText\": \"" + MessageText + "\",\n";
            jsonContent += "\"messageHash\": \"" + MessagesHash + "\"\n";
            jsonContent += "}\n";

            FileWriter writer
                    = new FileWriter("messages.json", true);

            writer.write(jsonContent);
            writer.write("\n");

            writer.close();

            System.out.println("Message stored successfully.");

        } catch (Exception e) {

            System.out.println("Error storing message: "
                    + e.getMessage());
        }
    }

    //method to output hr result after ...
    public String printMessage() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + createMessageHash()
                + "\nRecipient: " + recipient
                + "\nMessage: " + MessageText;
    }

    //adding sent messages
    public void addSentMessage() {

        sentMessages[sentCount] = MessageText;

        recipients[sentCount] = recipient;
        messageHashes[sentCount] = MessagesHash;
        messageIDs[sentCount] = messageID;

        sentCount++;
    }

    //adding stored messages
    public void addStoredMessage() {

        storedMessages[storedCount] = MessageText;

        recipients[storedCount] = recipient;
        messageHashes[storedCount] = MessagesHash;
        messageIDs[storedCount] = messageID;

        storedCount++;
    }

    //adding deleted messages
    public void addDisregardedMessage() {

        disregardedMessages[disregardedCount] = MessageText;
        disregardedCount++;
    }

    //return sent messages
    public static String[] getSentMessages() {

        return sentMessages;
    }

    //display longest message
    public static String displayLongestMessage() {

        String longest = "";

        for (int i = 0; i < storedCount; i++) {

            if (storedMessages[i] != null
                    && storedMessages[i].length() > longest.length()) {

                longest = storedMessages[i];
            }
        }

        return longest;
    }

    //search messages by recipient
    public static String searchByRecipient(String recipientNumber) {

        String results = "";

        for (int i = 0; i < storedCount; i++) {

            if (recipients[i] != null
                    && recipients[i].equals(recipientNumber)) {

                results += storedMessages[i] + "\n";
            }
        }

        return results;
    }

    //search by message id
    public static String searchMessageByID(String id) {

        for (int i = 0; i < storedCount; i++) {

            if (messageIDs[i] != null
                    && messageIDs[i].equals(id)) {

                return storedMessages[i];
            }
        }

        return "Message not found";
    }

    //delete using hash
    public static String deleteMessage(String hash) {

        for (int i = 0; i < storedCount; i++) {

            if (messageHashes[i] != null
                    && messageHashes[i].equals(hash)) {

                storedMessages[i] = null;

                return "Message deleted successfully";
            }
        }

        return "Message not found";
    }

    //read json file
    public static void readStoredMessages() {

        try {

            File file = new File("messages.json");

            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {

                System.out.println(scan.nextLine());
            }

            scan.close();

        } catch (Exception e) {

            System.out.println("Error reading file.");
        }
    }

    //method to see the total of the messaages that was sent successfully and those that werent
    public static int returnTotalMessages() {

        return totalMessagesSent;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return MessagesHash;
    }

}