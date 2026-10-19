/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG5121POE;

/**
 *
 * @author Kutlw
 */
import java.util.Scanner;

public class QuickChatApp {
    public static void main(String[] args){
     Scanner scanner = new Scanner(System.in);
     // register and log in
     Login registeredUser = null;
       while (registeredUser == null){
        System.out.println("\n--- Register a new account---");
        System.out.print("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username (must contain an underscore and be no "
                + "more than 5 characters)");
        String username = scanner.nextLine();
        System.out.print("Enter password (min 8 characters, a capital letter, "
                + "a number and a special character):");
        String password = scanner.nextLine();
        System.out.print("Enter South African cell phone number (e.g.+27838968976): ");
        String cellPhoneNumber = scanner.nextLine();
        
        Login attempt = new Login(firstName, lastName, username, password, cellPhoneNumber);
        System.out.println(attempt.registerUser());
          
           if (attempt.checkUserName() && attempt.checkPasswordComplexity() &&
                   attempt.checkCellPhoneNumber()){
               registeredUser = attempt;
           } else { 
               System.out.println("Please try registering again.\n");
           }
       }
       System.out.println("\n--- Login to your account ---");
       System.out.print("Enter username:");
       String loginUsername = scanner.nextLine();
       System.out.print("Enter password:");
       String loginPassword = scanner.nextLine();
       boolean loginStatus = registeredUser.loginUser(loginUsername, loginPassword);
       
       System.out.println(registeredUser.returnLoginStatus(loginStatus));
            if (!loginStatus){
                System.out.println("You must log in successfully to send messages."
                        + "Exiting.");
                scanner.close();
                return;
            }
       //Welcome ans set up messaging session
       System.out.println("\nWelcome to QuickChat.");
       System.out.println("How many messages would you like to send this session?");
       int numMessages = Integer.parseInt(scanner.nextLine().trim());
       int messagesEntered = 0;
       
    // numeric menu loop
    boolean quit = false;
    while (!quit){
        System.out.println("\nPlease choose an option:");
        System.out.println("1) Send Messages");
        System.out.println("2) Show recently sent messages");
        System.out.println("3) Quit");
        System.out.print("Enter your choice: ");
        
        String choice = scanner.nextLine().trim();
        switch (choice){
            case "1":
                if (messagesEntered >= numMessages){
                    System.out.println("You have already entered your allowed " +
                            numMessages + "message(s)");
                    break;
                }
               System.out.print("Enter recipient cell number (e.g +27718693002):");
               String recipient = scanner.nextLine().trim();
               System.out.print("Enter your message (max 250 characters): ");
               String messageText = scanner.nextLine();
               Message message = new Message(recipient, messageText);
               System.out.println(message.recipientCellStatusMessage());
                if (!message.checkRecipientCell()){
                    break;
                }
               System.out.println(message.messageLengthStatusMessage());
               if (!message.checkMessageLength()){
               break;
               }
               
           System.out.println("Message ID generated: " + message.getMessageID());
           System.out.println("Message Hash: " + message.getMessageHash());
           System.out.println("\nWhat would you like to do with this message?");
           System.out.println("1) Send Message");
           System.out.println("2) Stored Message to send later");
           System.out.println("3) Disregard Message");
           System.out.print("Enter your choice: ");
           String sendChoice = scanner.nextLine().trim();
           String result;
           switch (sendChoice){
               case "1":
                   result = message.sentMessage("send");
                break;
               case "2":
                   result = message.sentMessage("store");
                   break;
               case "3":
                   result = message.sentMessage("disregard");
                   break;
               default:
                   result = "Invalid choice.";
           }
          System.out.println(result);
            if (sendChoice.equals("1")){
            System.out.println("\n--- Message Details---");
            System.out.println("Message ID: "+ message.getMessageID());
            System.out.println("Message Hash: " + message.getMessageHash());
            System.out.println("Recipient: " + message.getRecipient());
            System.out.println("Message: " + message.getMessageText());
            }
          messagesEntered++;
          break; 
            case "2":
                System.out.println("Coming Soon.");
                 break;
            case "3":
                quit = true;
                
            System.out.println("Total messages sent this session: " + Message.returnTotalMessages());
            System.out.println("Goodbye!");
            break;
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }
    scanner.close();
    }
    
}
