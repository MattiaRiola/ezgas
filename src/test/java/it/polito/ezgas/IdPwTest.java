package it.polito.ezgas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.polito.ezgas.dto.IdPw;

public class IdPwTest {
	private String user = "User";
	private String pw = "Pw";
	
	IdPw idpw;
	
	@Test
	public void testUser() {
		idpw = new IdPw(user, pw);
		assertEquals(user, idpw.getUser());
		String newUser = "newUser";
		idpw.setUser(newUser);
		assertEquals(newUser, idpw.getUser());
	}//EndTest
	
	@Test
	public void testPw() {
		idpw = new IdPw(user, pw);
		assertEquals(pw, idpw.getPw());
		String newPw = "newPw";
		idpw.setPw(newPw);
		assertEquals(newPw, idpw.getPw());
	}//EndTest
	
}
