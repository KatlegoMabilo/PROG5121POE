/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PROG5121POE;

/**
 *
 * @author Kutlw
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message{

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    private static int totalMessagesCreated = 0;
    private static int totalMessagesSent = 0;
    private static List<Message>sentMessages= new ArrayList<>();
    
    public Message(String recipient, String messageText){
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageNumber = totalMessagesCreated;
        totalMessagesCreated++;
    }
    
    //--------getters-------
    
   public String getMessageID(){ return messageID;}
   public int getMessageNumber() {return messageNumber;}
   public String getRecipient() { return recipient;}
   public String getMessageText() { return messageText; }
   public String getMessageHash() { return messageHash;}
   
   /* random 10-digit numeric string to use as message ID */
   private String generateMessageID(){
   Random random = new Random();
   StringBuilder id = new StringBuilder();
      for (int i = 0; i < 10; i++){
          id.append(random.nextInt(10));
      }
      return id.toString();     
      }
   /*message ID is no more than 10 characters*/
   public boolean checkMessageID(){
   return messageID != null && messageID.length()<=10;}
   
   /**recipient cell number is no more than 10 characters*/
   public boolean checkRecipientCell(){
     if (recipient == null){
        return false; 
        }
        return recipient.matches("^\\+\\d{1,11}$")&& recipient.length() <=12;
      }
   // returns the success/failure message for the recipient cell check
   public String recipientCellStatusMessage(){
      if (checkRecipientCell()){
         return "Cell phone number successfully captured.";
           } else {
            return "Cell phone number is incorrectly formatted or does not " +
                    "contain an international code. Please correct the number " +
                    "and try again.";
                   }
          }
   // limites message to 250 characters
   public boolean checkMessageLength(){
     return messageText != null && messageText.length()<=250;
   }
   // returns the success/failure message for the length check
   public String messageLengthStatusMessage(){
      if (checkMessageLength()){
       return "Message ready to send.";
       } else {
         int over = messageText.length() - 250;
           return "Message exceeds 250 characters by " + over + "; please reduce the size.";
         }
          }
   // creates and returns the message hash
   public String createMessageHash(){
   String idPrefix = messageID.substring(0, Math.min(2,messageID.length()));
   
   String[] words = messageText.trim().split("\\s+");
     String firstWord = words.length > 0 ? words[0].replaceAll("[^a-zA-Z]", ""): "";
     String lastWord = words.length > 0 ? words[words.length - 1].replaceAll("[^a-zA-Z]", ""): "";
      this.messageHash = (idPrefix + ":"+ messageNumber + ":" + firstWord +
              lastWord).toUpperCase();
           return this.messageHash;
           }
   // lets user choose what happens to the message
   public String sentMessage(String choice){
     if (choice == null){
        return "Invalid choice.";
        }
        switch (choice.trim().toLowerCase()){
            case "send":
                sentMessages.add(this);
                totalMessagesSent++;
                return "Message successfully sent.";
            case "store":
                storeMessage();
                return "Message successfully stored.";
            case "disregard":
                return "Press 0 to delete the message.";
            default: 
                return "Invalid choice.";
        }
     }
   // Returns the full details of every message that has been sent
   public static String printMessages(){
      if (sentMessages.isEmpty()){
          return "No messages have been sent.";
      }
      StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages){
         sb.append("Message ID:").append(m.getMessageID()).append("\n"); 
         sb.append("Message Hash: ").append(m.getMessageHash()).append("\n");
         sb.append("Recipient:").append(m.getRecipient()).append("\n");
         sb.append("Message:").append(m.getMessageText()).append("\n");
         sb.append("-----------------\n");
        }
        return sb.toString();
    }
   //returns the total number of messages that have been sent.
   public static int returnTotalMessages(){
       return totalMessagesSent;
   }
   public static void resetMessageStore(){
     totalMessagesCreated = 0;
     totalMessagesSent = 0;
     sentMessages.clear();
   }
   public boolean storeMessage(){
     try (FileWriter writer = new FileWriter("message.json", true)){
        String json = "{" + "\"messageID\":\"" + messageID + "\"," +
                "\"messageNumber\":" + messageNumber + "," + 
                "\"recipient\":\"" + recipient + "\"," + 
                "\"message\":\"" + messageText.replace("\"","\\\"") + "\"," +
                "\"messageHash\":\"" + (messageHash != null ? messageHash : createMessageHash()) +
                "\"" + "}" + System.lineSeparator();
                   writer.write(json);
                   return true;
     } catch (IOException e) {
        System.out.println("Could not store message:" + e.getMessage());
     return false;
     }      
   }
}
    

