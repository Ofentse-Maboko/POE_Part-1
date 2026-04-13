/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;

import java.util.Scanner;

/**
 *
 * @author matom
 */
public class Quickchat {
    public static void main(String[] args) {

              //  Allows program to take data from the user
		Scanner user = new Scanner(System.in);

                //this is written so that the code can be called in the console
		System.out.println("\n======Menu======");
		
		System.out.println("Enter NAME and Surname");
		String namesurname = user.nextLine();
		System.out.println("Enter username");
		String userName = user.nextLine();
		System.out.println("enter password");
		String passWord = user.nextLine();
		System.out.println("enter cellphone number");
		String cellPhoneNumber= user.nextLine();
		

		String outputmessages = Login2.registeredUser(namesurname,userName,passWord,cellPhoneNumber);
		
		
		System.out.println("output message");
		
		if(outputmessages.contains("not correct"))   {
			
			System.out.println("Please make sure everything is correct");
			return;
			
		}
                //This allows the questions to be printed in the console
		System.out.println("Enter username");
		String LoginUsername = user.next();
		
		System.out.println("Enter password");
		String LoginPassword = user.next();
		
	}

}
