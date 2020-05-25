package it.polito.ezgas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.polito.ezgas.entity.User;

public class UserEntityTest {
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
	
	private User usr;
	
	@Test
	public void testUserId() {
			this.usr = new User (this.userName,this.password,this.email,this.reputation);
			this.usr.setUserId(userId);
			assertEquals(this.userId,this.usr.getUserId(),"Error: User Entity - get/set UserId issue"); 
	}
	@Test
	public void testUserName() {
		this.usr = new User (this.userName,this.password,this.email,this.reputation);
		assertEquals(this.userName,this.usr.getUserName(),"Error: User Entity - get/costructor UserName issue"); 
		this.usr.setUserName(userName_new);
		assertEquals(this.userName_new,this.usr.getUserName(),"Error: User Entity - get/set UserName issue"); 
	}
	@Test
	public void testPassword() {
		this.usr = new User (this.userName,this.password,this.email,this.reputation);
		assertEquals(this.password,this.usr.getPassword(),"Error: User Entity - get/costructor password issue"); 
		this.usr.setPassword(password_new);
		assertEquals(this.password_new,this.usr.getPassword(),"Error: User Entity - get/set password issue"); 
		
	}
	@Test
	public void testEmail() {
		this.usr = new User (this.userName,this.password,this.email,this.reputation);
		assertEquals(this.email,this.usr.getEmail(),"Error: User Entity - get/costructor email issue");
		this.usr.setEmail(email_new);
		assertEquals(this.email_new,this.usr.getEmail(),"Error: User Entity - get/set email issue"); 
	}
	@Test
	public void testReputation() {
		this.usr = new User (this.userName,this.password,this.email,this.reputation);
		assertEquals(this.reputation,this.usr.getReputation(),"Error: User Entity - get/costructor reputation issue"); 
		this.usr.setReputation(reputation_new);
		assertEquals(this.reputation_new,this.usr.getReputation(),"Error: User Entity - get/set reputation issue"); 
		
	}
	@Test
	public void testAdmin() {
		this.usr = new User (this.userName,this.password,this.email,this.reputation);
		this.usr.setAdmin(admin);
		assertEquals(this.admin,this.usr.getAdmin(),"Error: User Entity - get/set admin issue"); 
	}

	
	
	

}
