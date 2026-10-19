/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG5121POE;

/**
 *
 * @author Kutlw
 */
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class MessageTest {
    @Before
    public void resetBeforeEachTest(){
     Message.resetMessageStore();
    }
   // Message length
    @Test
    public void testMessageLengthWithLimit(){
        Message message = new Message("+27718693002", "Hi Mike, can you join us for "
                + "dinner tonight?");
        assertTrue(message.checkMessageLength());
         assertEquals("Message ready to send.", message.messageLengthStatusMessage());
    }
    @Test
    public void testMessageLengthExceedsLimit(){
    String longMessage = "A".repeat(300);
    Message message = new Message("+27718693002", longMessage);
    assertFalse(message.checkMessageLength());
    assertEquals("Message exceeds 250 characters by 50; please reduce the size.",
            message.messageLengthStatusMessage());
            }
    //Recipient cell number 
    @Test
    public void testRecipientCellCorrectlyFormatted(){
    Message message = new Message("+27718693002","Hi Mike, can you join us for "
                + "dinner tonight?" );
       assertTrue(message.checkRecipientCell());
       assertEquals("Cell phone number successfully captured.", message.recipientCellStatusMessage());
            } 
    @Test
    public void testRecipientCellIncorrectlyFormatted(){
    Message message = new Message("0857975889", "Hi Keegan, did you receive the payment?");
    assertFalse(message.checkRecipientCell());
    assertEquals("Cell phone number is incorrectly formatted or does not contain "
            + "an international code. Please correct the number and try again.",
            message.recipientCellStatusMessage());  
    }
    // Message hash
    @Test
    public void testMessageHashCorrect(){
    Message message = new Message("+27718693002", "Hi Mike, can you join us for "
                + "dinner tonight?" );
    String hash = message.createMessageHash();
    String expectedPrefix = message.getMessageID().substring(0, 2);
    assertEquals((expectedPrefix + ":0:HITONIGHT.").toUpperCase(),hash);
            }
    //Message ID 
    @Test
    public void testMessageIDWithinLimit(){
    Message message = new Message("+27718693002", "Hi Mike, can you join us for "
                + "dinner tonight?");
    assertTrue(message.checkMessageID());
    assertTrue(message.getMessageID().length() <= 10);
    }
   // sent message outcomes
    @Test
    public void testMessge1_SelectSend(){
    Message message1 = new Message("+27718693002","Hi Mike, can you join us for "
                + "dinner tonight?" );
    String result = message1.sentMessage("send");
    assertEquals("Message successfully sent.", result);
    assertEquals(1, Message.returnTotalMessages());
        }
    @Test
    public void testMessage2_SelectDisregard(){
    Message message2 = new Message("0857975889", "Hi Keegan, did you receive the payment?");
    String result = message2.sentMessage("disregard");
    assertEquals("Press 0 to delete the message.", result);
    assertEquals(0, Message.returnTotalMessages());
    }
    @Test 
    public void testSelectStore(){
        Message message = new Message("+27718693002","Hi Mike, can you join us for "
                + "dinner tonight ");
        String result = message.sentMessage("store");
        assertEquals("Message successfully stored.", result);
    }
    // total messages sent
    @Test
    public void testReturnTotalMessagesSentAcrossBoth(){
        Message message1 = new Message("+27718693002","Hi Mike, can you join us for "
                + "dinner tonight?" );
       message1.sentMessage("send");
       Message message2 = new Message( "0857975889", "Hi Keegan, did you receive the payment?");
       message2.sentMessage("disregard");
       
       assertEquals(1, Message.returnTotalMessages());
    }
    }

