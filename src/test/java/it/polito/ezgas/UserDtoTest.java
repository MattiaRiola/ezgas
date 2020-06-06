package it.polito.ezgas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import it.polito.ezgas.dto.UserDto;


public class UserDtoTest {
	private Integer userId = 10;
	private String userName = "username";
	private String password = "password";
	private String email = "email@email.com";
	private Integer reputation = 4;
	private Boolean admin = false;
//	private Integer userId_new = 11; 
	private String userName_new = "username2";
	private String password_new = "password2";
	private String email_new = "email2@email.com";
	private Integer reputation_new = 5;
//	private Boolean admin_new = true;
	
	private UserDto usr;
	
	@Test
	public void testUserId() {
			this.usr = new UserDto (userId, userName, password, email, reputation);
			this.usr.setUserId(userId);
			assertEquals(this.userId.intValue(),this.usr.getUserId().intValue(),"Error: User dto- get/set UserId issue"); 
	}
	@Test
	public void testUserName() {
		this.usr = new UserDto (userId, userName, password, email, reputation);
		assertEquals(this.userName,this.usr.getUserName(),"Error: User dto- get/costructor UserName issue"); 
		this.usr.setUserName(userName_new);
		assertEquals(this.userName_new,this.usr.getUserName(),"Error: User dto- get/set UserName issue"); 
	}
	@Test
	public void testPassword() {
		this.usr = new UserDto (userId, userName, password, email, reputation);
		assertEquals(this.password,this.usr.getPassword(),"Error: User dto- get/costructor password issue"); 
		this.usr.setPassword(password_new);
		assertEquals(this.password_new,this.usr.getPassword(),"Error: User dto- get/set password issue"); 
		
	}
	@Test
	public void testEmail() {
		this.usr = new UserDto (userId, userName, password, email, reputation);
		assertEquals(this.email,this.usr.getEmail(),"Error: User dto- get/costructor email issue");
		this.usr.setEmail(email_new);
		assertEquals(this.email_new,this.usr.getEmail(),"Error: User dto- get/set email issue"); 
	}
	@Test
	public void testReputation() {
		this.usr = new UserDto (userId, userName, password, email, reputation);
		assertEquals(this.reputation.intValue(),this.usr.getReputation().intValue(),"Error: User dto- get/costructor reputation issue"); 
		this.usr.setReputation(reputation_new);
		assertEquals(this.reputation_new.intValue(),this.usr.getReputation().intValue(),"Error: User dto- get/set reputation issue"); 
		
	}
	@Test
	public void testAdmin() {
		this.usr = new UserDto (userId, userName, password, email, reputation);
		this.usr.setAdmin(admin);
		assertEquals(this.admin,this.usr.getAdmin(),"Error: User dto- get/set admin issue"); 
	}
}
