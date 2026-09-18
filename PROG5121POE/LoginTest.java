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
import static org.junit.Assert.*; 
public class LoginTest {
    
    @Test
    public void testUsernameCorrectlyFormatted(){
      Login login = new Login("Kyle", "Smith","kyl_1" , "Ch&&sec@ke99!","+27838968976");
             assertTrue(login.checkUserName());
              }
    
    @Test 
    public void testUsernameIncorrectlyFormatted(){
    Login login = new Login("Kyle","Smith","kyle!!!!!!!", "Ch&&sec@ke99!","+27838968976");
          assertFalse(login.checkUserName());
            }
    
    @Test
    public void testPasswordMeetsComplexity(){
    Login login = new Login("Kyle","Smith","kyl_1" ,"Ch&&sec@ke99!","+27838968976");
    assertTrue(login.checkPasswordComplexity());
    }
    
    @Test
    public void testPasswordDoesNotMeetComplexity(){
    Login login = new Login("Kyle","Smith","kyl_1" ,"Ch&&sec@ke99!","+27838968976");
    assertFalse(login.checkPasswordComplexity());
    }
    
    @Test
    public void testCellPhoneNumberCorrectlyFormatted(){
     Login login = new Login("Kyle","Smith","kyl_1" ,"Ch&&sec@ke99!","+27838968976");
    assertTrue(login.checkCellPhoneNumber());
    }
    
  @Test
    public void testCellPhoneNumberIncorrectlyFormatted(){
     Login login = new Login("Kyle","Smith","kyl_1" ,"Ch&&sec@ke99!","+27838968976");
    assertFalse(login.checkCellPhoneNumber());
    }
    
    @Test
    public void testLoginSuccessful(){
     Login login = new Login("Kyle","Smith","kyl_1" ,"Ch&&sec@ke99!","+27838968976");
     boolean status = login.loginUser("kyl_1","Ch&&sec@ke99!" );
    assertTrue(status);
    }
    
     @Test
    public void testLoginFailed(){
     Login login = new Login("Kyle","Smith","kyl_1" ,"Ch&&sec@ke99!","+27838968976");
     boolean status = login.loginUser("kyl_1","wrongPassword1!" );
    assertFalse(status);
    }
    
    @Test
    public void testRegisterUserSuccessMessage(){
    Login login = new Login("Kyle","Smith","kyl_1","Ch&&sec@ke99!","+27838968976");
    assertEquals("User kyl_1 successfully registered!!", login.registerUser());
            }
    
    @Test
    public void testRegisterUsernameFailedMessage(){
    Login login = new Login("Kyle","Smith","kyl_1","Ch&&sec@ke99!","+27838968976");
    assertEquals("Username is not correctly formmatted;"
            + "please ensure that your username contains an underscore and"
            + "is no more than 5 characters in length ", login.registerUser());
            }
    
    @Test
    public void testRegisterUserPasswordFailedMessage(){
    Login login = new Login("Kyle","Smith","kyl_1","Ch&&sec@ke99!","+27838968976");
    assertEquals("Password is not correctly formmatted;"
            + "please ensure that the password contains at least 8 characters, "
            + "a capital letter, a number and a special character. ", login.registerUser());
            }
    
    @Test
    public void testReturnLoginStatusSucceess(){
    Login login = new Login("Kyle","Smith","kyl_1","Ch&&sec@ke99!","+27838968976");
    assertEquals ("Welcome Kyle, Smit it is great to see you again.", login.returnLoginStatus(true));
            }
    
    @Test
    public void testReturnLoginStatusFail(){
    Login login = new Login("Kyle","Smith","kyl_1","Ch&&sec@ke99!","+27838968976");
    assertEquals ("Welcome Kyle, Smit it is great to see you again.", login.returnLoginStatus(false));
            }

    private void assertTrue(boolean checkUserName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertFalse(boolean checkUserName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(String user_kyl_1_successfully_registered, String registerUser) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
