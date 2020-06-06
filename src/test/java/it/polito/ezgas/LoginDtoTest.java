package it.polito.ezgas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.polito.ezgas.dto.LoginDto;

public class LoginDtoTest {
	Integer userId = new Integer(1);
    String userName = "TestName";
    String token = "Token";
    String email = "Email";
    Integer reputation = new Integer(1);
    //Boolean admin = false;
    
    LoginDto testLogin;
    
    @Test
    public void testUserId() {
    	testLogin = new LoginDto(userId, userName, token, email, reputation);
    	assertEquals(userId.intValue(),testLogin.getUserId().intValue());
    	testLogin.setUserId(new Integer(2));
    	assertEquals(2 ,testLogin.getUserId().intValue());
    }//EndTest.
    
	@Test
	public void testUserName() {
		testLogin = new LoginDto(userId, userName, token, email, reputation);
		assertEquals(this.userName,testLogin.getUserName()); 
		String newUserName = "NewName";
		testLogin.setUserName(newUserName);
		assertEquals(newUserName, testLogin.getUserName()); 
	}//EndTest.
	
	@Test
	public void testToken() {
		testLogin = new LoginDto(userId, userName, token, email, reputation);
		assertEquals(this.token, testLogin.getToken()); 
		String newToken = "NewToken";
		testLogin.setToken(newToken);
		assertEquals(newToken, testLogin.getToken()); 
	}//EndTest.
	
	@Test
	public void testEamil() {
		testLogin = new LoginDto(userId, userName, token, email, reputation);
		assertEquals(this.email, testLogin.getEmail()); 
		String newMail = "NewMail";
		testLogin.setEmail(newMail);
		assertEquals(newMail, testLogin.getEmail()); 
	}//EndTest.
	
	@Test
	public void testReputation() {
		testLogin = new LoginDto(userId, userName, token, email, reputation);
		assertEquals(this.reputation.intValue(), testLogin.getReputation().intValue());
		Integer reputation_new = new Integer(5);
		testLogin.setReputation(reputation_new);
		assertEquals(reputation_new.intValue(),this.testLogin.getReputation().intValue()); 
		
	}//EndTest.
	
	@Test
	public void testAdmin() {
		testLogin = new LoginDto(userId, userName, token, email, reputation);
		testLogin.setAdmin(false);
		assertEquals(false, testLogin.getAdmin());
		testLogin.setAdmin(true);
		assertEquals(true, testLogin.getAdmin());
	}//EndTest.
}
