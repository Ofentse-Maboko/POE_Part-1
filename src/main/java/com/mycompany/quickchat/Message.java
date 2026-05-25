/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

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
        
        if (MessageText.length()  <=250)  {
            
            System.out.println("message ready to send");
            return true;
        }
        else {
            int excess = MessageText.length() - 250;
            System.out.println("message exceeds 250 characters by"+ excess+ "; please reduce the size");
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
    public String createMessageHash()  {

    if (messageID == null || MessageText == null) {
        return "INVALID_HASH";
    }

    String firstTwo = messageID.length() >= 2 ? messageID.substring(0, 2) : messageID;
    String msgNum = String.valueOf(MessageNumber);
    String[] words = MessageText.split(" ");

    if (words.length == 0 || words[0].isEmpty()) {
        return firstTwo + ":" + msgNum + ":EMPTY";
    }

    String firstWord = words[0];
    String lastWord = words[words.length - 1];

    return (firstTwo + ":" + msgNum + ":" + firstWord + lastWord).toUpperCase();
}
    //generate the ids using ranform
    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
        
    }
    //methoos for sending messages
    public String sentMessage() {
        
       Scanner Scan = new Scanner(System.in);
      boolean isValidPhone = recipient.matches("\\+27\\d{9}");
        
       System.out.println(" \nWhat would you like to do with");
       System.out.println(" 1.Send Message");
       System.out.println("0.Disregard Message(Delete) -  ");
       System.out.println("3.Store messages to send later ");
       System.out.println("Choose options(1/0/3): ");
       
       int choice = Scan.nextInt();
       if (choice == 1) {
       if (isValidPhone)  {
           totalMessagesSent++;
           return "Message successfully sent";
       }
       else {
           return "Cannot send messages: Invalid phone number! please use +27 followed by 9 digits";
       }
       }
       else if(choice == 0) {

    return"Press 0 to delete the message.Message deleted.";
}
    else if (choice == 3)  {
    storeMessage();
    return "messages successfully stored";
}
else {
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

        java.io.FileWriter writer =
                new java.io.FileWriter("messages.json", true);

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
        
        
    
    //method to see the total of the messaages that was sent successfully and those that werent
    public static int returnTotalMessages() {
        
        return totalMessagesSent;
    }
    public String getMessageID() { return messageID; }
    public String getMessageHash() {return MessagesHash;}
    
    
    
    
}
