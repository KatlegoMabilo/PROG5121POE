/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG5121POE;

/**
 *
 * @author Kutlw
 */
import java.util.regex.Pattern;

public class Login {
    // fields to store registered user data
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;
    
     public Login(String firstName, String lastName, String username, String password, String cellPhoneNumber) {
         this.firstName = firstName;
         this.lastName = lastName;
         this.username = username;
         this.password = password;
         this.cellPhoneNumber = cellPhoneNumber;
         }
     
    // the getters 
     public String getFirstName(){ return firstName;}
     public String getLastName() { return lastName;}
     public String getUsername() { return username;}
     public String getPassword(){ return password;}
     public String getCellPhoneNumber(){ return cellPhoneNumber;}
     
     /**
      *username should contains an underscore and is no more than 
      *five characters long.
     * @return 
      */
      public boolean checkUserName() {
       if (username == null){ 
          return false;
       } 
       return username.contains("_") && username.length() <= 5;
      }
      /**
       *password should at least 8 characters long
       *and contains a capital letter, a number
       *and a special character
     * @return 
       */
      public boolean checkPasswordComplexity(){
          if (password == null) {
              return false;
          }
          boolean hasMinLength = password.length() >= 8;
          boolean hasCapital = Pattern.compile("[A - Z]").matcher(password).find();
          boolean hasNumber = Pattern.compile("[0-9]").matcher(password).find();
          boolean hasSpecialChar = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
            return hasMinLength && hasCapital && hasNumber && hasSpecialChar;
      }
      /**
       * cell phone number should contain  the South African
       * international country code (+27) followed by the number
       * and is no more than 10 characters after the country code.
     * @return 
       */
      public boolean checkCellPhoneNumber() {
       if (cellPhoneNumber == null){
       return false;
       }
       String regex = "^\\+27\\d{1,9}$";
       return Pattern.matches(regex, cellPhoneNumber);
          }
      /**
       * registers the user and returns the appropriate output message
       * depending on which conditions have or have not been met. 
     * @return 
       */
      public String registerUser(){
       if (!checkUserName()){
       return "Username is not correctly formatted; "
               + "please ensure that your username contains an underscore "
               + "and is no more than 5 characters in length. ";
               }
       if (!checkPasswordComplexity()){
       return "Password is not formatted correctly; "
               + "please ensure that the password contains at least 8 characters "
               + "a capital letter, a number and a special character.";
       }
       if (!checkCellPhoneNumber()){
       return "Cell phone number incorrectly formatted or does not contain an international code; "
               + "please correct the number and try again.";
       }
       return "User " + username + "successfully registered !!";
           }
      /**
       * verifies that the entered username and password match the
       * details captured when the user registered.
     * @param enteredUsername
     * @param enteredPassword
     * @return 
       */
      public boolean loginUser(String enteredUsername, String enteredPassword){
      if(enteredUsername == null  || enteredPassword == null){
      return false;
      }
        return enteredUsername.equals(this.username) && enteredPassword.equals(this.password);
          }
      /**
       * returns the appropriate message for a successful or failed login.
     * @param loginStatus
     * @return 
       */
      public String returnLoginStatus(boolean loginStatus){
      if (loginStatus){
      return "Welcome " + firstName + " " + lastName + " " + "it is great to see you again.";
      } else {
          return "Username or password incorrect, please try again.";
        }
     }
}
    
