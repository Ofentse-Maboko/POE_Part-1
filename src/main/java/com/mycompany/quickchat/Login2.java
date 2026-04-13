/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

/**
 *
 * @author matom
 */
    public class Login2 {
    static User2 registeredUser = new User2();
	    
	public static boolean checkUserName(String userName) {
		return (userName.length()  <=5  || !userName.contains("_"));
			
	}
        
        // checking if the password is less than 8 characters if not its wrong
	public static boolean checkPasswordComplexity(String passWord)   {
		
		if(passWord.length()   <  8) 
			return false;
	
		//checking for uppercase letter
    boolean hasUpper = passWord.matches(".*[A-Z].*");
    boolean hasNumber = passWord.matches(".*[0-9].*");
    boolean hasSpecial = passWord.matches(".*[^a-zA-Z0-9].*");

    return hasUpper && hasNumber && hasSpecial;

		
	}
        
        //The number must begin with specified digits if it does not it is wrong 
	public static boolean checkCellPhoneNumber(String cellphone)  {
		
		if(cellphone.startsWith("+27")  && cellphone.length()  == 12)  {
			
			String numbers = cellphone.substring(3);
			if(numbers.matches("\\d+")) {
				return true;
				
			}
		}
		
		return false;
		
	}
	
        //The correct output to receive if the inputs are incorrect
	public static String registeredUser(String namesurname,String userName,String passWord,String cellPhone)  {
		String errorMessages = "";
		if(!checkUserName(userName)) {
			errorMessages += "Username is not correctly formatted:  Please ensure that your Username contains an underscore ";
			
		}
		if(!checkPasswordComplexity(passWord)) {
			errorMessages += "Password is not correctly formatted:  Please ensure that your password is atleast five characters long ";
		
	}
		if(!checkCellPhoneNumber(cellPhone)) {
			errorMessages += "CellPhone number is not correctly formatted:  Please ensure that your Number contains +27 and is 12 numbers long ";
	
	
		}
	
		if(!errorMessages.isEmpty()) {
			return errorMessages; 
		}
		
		
	//The correct outputs if the input is correct 
	
	String successMessages = "";
	successMessages += "Username successfully captured\n";
	successMessages += "Password successfully captured\n";
	successMessages += "Cellphone successfully captured\n";
	successMessages += "CellPhone Number successfully captured\n";
	successMessages += "Welcome"  + namesurname +"   "+ "A pleasure to meet your aquantance.";
	
	registeredUser.userName = userName;
	registeredUser.passWord = passWord;
	registeredUser.nameSurname = namesurname;
	registeredUser.cellPhone =cellPhone;
	
	return successMessages;
	
	}

        //check for password conditions
	public static boolean LoginUser(String UserName ,String passWord)  {
		
            
		if(UserName.equals(registeredUser.userName) && 
		passWord.equals(registeredUser.passWord)) {
			return true;
			
		}else {
		
			return false;
			
		}
				
	}
	
	public	static String returnLoginStatus(boolean isLoggedIn)  {
			
                    //Checking if the user is the real one and welcoming them if they are 
			if(isLoggedIn)  {
				
				String nameSurname = registeredUser.nameSurname != null  ?registeredUser.nameSurname  : "";
				return "Welcome Back "+nameSurname+ "    " +"It is a pleasure to see you again";
				
				
			}else  {
				
				return "Login failed";
				
			}
			
	}
}


