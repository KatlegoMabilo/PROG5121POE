/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG5121POE;
import java.util.Scanner;
/**
 *
 * @author Kutlw
 */
public class main {
   public static void main(String[] args){
       try (Scanner scanner = new Scanner(System.in)) {
           Login registeredUser = null;
           
           System.out.println("Welcome to the Registration and Login App");
           
           //loop untill registration succeeds
           while (registeredUser == null){
               System.out.println("\n--- Register a new account---");
               
               System.out.print("Enter first name: ");
               String firstName = scanner.nextLine();
               System.out.print("Enter your last name: ");
               String lastName = scanner.nextLine();
               System.out.println("Enter username (must contain an underscore and "
                       + "be no more than 5 charcters): ");
               String username = scanner.nextLine();
               System.out.print("Enter password (min 8 characters, a capital letter, "
                       + "a number and a special character): ");
               String password = scanner.nextLine();
               System.out.print("Enter South Afican cell phone number (eg +27838968976): ");
               String cellPhoneNumber = scanner.nextLine();
               
               Login attempt = new Login(firstName, lastName, username, password, cellPhoneNumber);
               String results = attempt.registerUser();
               System.out.println(results);
               
               if (attempt.checkUserName() && attempt.checkPasswordComplexity() && attempt.checkCellPhoneNumber()){
                   registeredUser = attempt;
               } else {
                   System.out.println("Please try registering again.");
               }
           }
           // login section
           System.out.println("\n--- Login to your account");
           System.out.print("Enter username:");
           String LoginUsername  = scanner.nextLine();
           System.out.print("Enter password: ");
           String LoginPassword = scanner.nextLine();
           boolean LoginStatus = registeredUser.loginUser(LoginUsername, LoginPassword);
           System.out.println(registeredUser.returnLoginStatus(LoginStatus));
       }
   }
}
