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
	
	private User usr;
	
	@Test
	public void testUserId() {
			this.usr = new User (this.userName,this.password,this.email,this.reputation);
			this.usr.setUserId(userId);
			assertEquals(this.userId,this.usr.getUserId()); 
	}
	@Test
	public void testUserName() {
		this.usr = new User (this.userName,this.password,this.email,this.reputation);
		assertEquals(this.userName,this.usr.getUserName()); 
	}
	@Test
	public void testPassword() {
		this.usr = new User (this.userName,this.password,this.email,this.reputation);
		assertEquals(this.password,this.usr.getPassword()); 
	}
	@Test
	public void testEmail() {
		this.usr = new User (this.userName,this.password,this.email,this.reputation);
		assertEquals(this.email,this.usr.getEmail()); 
	}
	@Test
	public void testReputation() {
		this.usr = new User (this.userName,this.password,this.email,this.reputation);
		assertEquals(this.reputation,this.usr.getReputation()); 
	}
	@Test
	public void testAdmin() {
		this.usr = new User (this.userName,this.password,this.email,this.reputation);
		this.usr.setAdmin(admin);
		assertEquals(this.admin,this.usr.getAdmin()); 
	}

	
	
	

}
