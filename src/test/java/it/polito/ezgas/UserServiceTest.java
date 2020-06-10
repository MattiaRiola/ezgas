package it.polito.ezgas;
import it.polito.ezgas.converter.Converter;
import it.polito.ezgas.converter.impl.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.UserServiceimpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceTest {
	@Autowired
    private UserRepository userRep;
	private UserServiceimpl testUserService;
	private Converter<User,UserDto> userConverter;
	
	
	
	
	private User testUser;
	private User testUser0;
	private User testUser2;
	private User testUser3;
	
	private IdPw credentials;
	private IdPw credentials0;
	private IdPw credentials2;
	private IdPw credentials3;
	
	private LoginDto loginDto;
	private LoginDto loginDto0;
	private LoginDto loginDto2;
	private LoginDto loginDto3;
	
	/**
	 * Generate a random id (from 0 to Integer.MAX_VALUE) that isn't the repository 
	 * @return an id that doesn't exist in the repository
	 */
	private int generateNotExistingId() {
		Integer id = (int) (Math.random() * Integer.MAX_VALUE);
		while(null != userRep.findById(id))
			id = (int) (Math.random() * Integer.MAX_VALUE);
		return id;
	}
	private void compareUsers(User expected,User actual, String note) {
		if(actual == null && expected == null) {
			fail("Error - " + note + " : expected AND actual are null");
			return;
		}
		if(actual == null) {
			fail("Error - " + note + " : actual is null");
			return;
		}
		if(expected == null) {
			fail("Error - " + note + " : expected is null");
			return;
		}
		assertEquals("Error - " + note + ": user id is wrong", expected.getUserId(),actual.getUserId());
		assertEquals("Error - "+ note + " : user admin is wrong", expected.getAdmin(),actual.getAdmin());
		assertEquals("Error - "+ note + " : user Email is wrong", expected.getEmail(),actual.getEmail());
		assertEquals("Error - "+ note + " : user UserName is wrong", expected.getUserName(),actual.getUserName());
		assertEquals("Error -" + note + " : user password is wrong", expected.getPassword(),actual.getPassword());
		assertEquals("Error - " + note + " : user reputation is wrong", expected.getReputation(),actual.getReputation());
	}
	private void compareUsers(UserDto expected,UserDto actual,String note) {
		if(actual == null && expected == null) {
			fail("Error - " + note + " : expected AND actual are null");
			return;
		}
		if(actual == null) {
			fail("Error - " + note + " : actual is null");
			return;
		}
		if(expected == null) {
			fail("Error - " + note + " : expected is null");
			return;
		}
		assertEquals("Error - " + note + ": user id is wrong", expected.getUserId(),actual.getUserId());
		assertEquals("Error - "+ note + " : user admin is wrong", expected.getAdmin(),actual.getAdmin());
		assertEquals("Error - "+ note + " : user Email is wrong", expected.getEmail(),actual.getEmail());
		assertEquals("Error - "+ note + " : user UserName is wrong", expected.getUserName(),actual.getUserName());
		assertEquals("Error -" + note + " : user password is wrong", expected.getPassword(),actual.getPassword());
		assertEquals("Error - " + note + " : user reputation is wrong", expected.getReputation(),actual.getReputation());
	}
	private void compareLogin(LoginDto expected, LoginDto actual,String note) {
		if(actual == null && expected == null) {
			fail("Error - " + note + " : expected AND actual are null");
			return;
		}
		if(actual == null) {
			fail("Error - " + note + " : actual is null");
			return;
		}
		if(expected == null) {
			fail("Error - " + note + " : expected is null");
			return;
		}
		assertEquals("Error - " + note + ": user id is wrong", expected.getUserId(),actual.getUserId());
		assertEquals("Error - "+ note + " : user admin is wrong", expected.getAdmin(),actual.getAdmin());
		assertEquals("Error - "+ note + " : user Email is wrong", expected.getEmail(),actual.getEmail());
		assertEquals("Error - "+ note + " : user UserName is wrong", expected.getUserName(),actual.getUserName());
		assertEquals("Error - " + note + " : user reputation is wrong", expected.getReputation(),actual.getReputation());
//		assertEquals(expected.getToken(),actual.getToken(),"Error - " + note + " : user token is wrong");
	}
	
	@Before
	public void setup() {
		testUserService = new UserServiceimpl(userRep);
		userConverter = new UserConverter();
		
		testUser = new User("test","password","test@test.test",-3);;
		testUser.setAdmin(true);
		
		testUser0 = new User("test0","password0","test0@test0.test0",0);
		testUser0.setAdmin(false);
		
		testUser2 = new User("test2","password2","test2@test2.test2",2);
		testUser2.setAdmin(false);
		
		testUser3 = new User("test3","password3","test3@test3.test3",3);
		testUser3.setAdmin(false);
		
		credentials = new IdPw("test@test.test","password");
		credentials0 = new IdPw("test0@test0.test0","password0");
		credentials2 = new IdPw("tes2t@test2.test2","password2");
		credentials3 = new IdPw("test3@test3.test3","password3");
		
		loginDto = new LoginDto(-1,"test","","test@test.test",-3);
		loginDto.setAdmin(true);
		loginDto0 = new LoginDto(-1,"test0","","test0@test0.test0",0);
		loginDto0.setAdmin(false);
		loginDto2 = new LoginDto(-1,"test2","","test2@test2.test2",2);
		loginDto2.setAdmin(false);
		loginDto3 = new LoginDto(-1,"test3","","test3@test3.test3",3);
		loginDto3.setAdmin(false);
	}
	
	@Test
	public void testgetUserById() {
		userRep.save(testUser);
		User expected = userRep.findById(testUser.getUserId());
		
		try {
			compareUsers(userConverter.convertToDto(expected),testUserService.getUserById(expected.getUserId()),"search testUser");
		}catch( InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws an invalidUserException");
		}
		
		userRep.save(testUser0);
		userRep.save(testUser2);
		expected = userRep.findById(testUser0.getUserId());
		try {
			compareUsers(userConverter.convertToDto(expected),testUserService.getUserById(expected.getUserId()),"search testUser");
		}catch( InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws an invalidUserException");
		}
		
		
		
		try {
			testUserService.getUserById(null);			
			fail("User Id is invalid (null) but the method doesn't throw the invalidUserException");
		}catch( InvalidUserException invalidUserException) {
			assertEquals(0,0);
		}
		try {
			testUserService.getUserById(-100);			
			fail("Error: User Id is invalid (<0) but the method doesn't throw the invalidUserException");
		}catch( InvalidUserException invalidUserException) {
			assertEquals(0,0);
		}
		
		Integer id = generateNotExistingId();
		System.out.println("id found: "+id);
		try {
			assertNull("Error: User id isn't in the repository but the method doesn't return null",testUserService.getUserById(id));
		}catch( InvalidUserException invalidUserException) {
			fail("Error: User Id is valid and not found in the repository but the method throws the invalidUserException");
		}
		
		
	}
	
	@Test
	public void testSaveUser() {
		userRep.deleteAll();
		userRep.save(testUser);
		assertNull("Error: saved a null User and the saveUser method doesn't return null",testUserService.saveUser(null));
		UserDto actual = testUserService.saveUser(userConverter.convertToDto(testUser0));
		User expected = userRep.findByEmail(testUser0.getEmail());
		compareUsers(userConverter.convertToDto(expected),actual,"saving user doesn't return the saved user");
		
	}
	
	@Test
	public void testGetAllUsers() {
		userRep.deleteAll();
		assertEquals("Error: not empty List", 0,testUserService.getAllUsers().size());
		testUserService.saveUser(userConverter.convertToDto(testUser));
		assertEquals("Error: wrong size of the List", 1,testUserService.getAllUsers().size());
		testUserService.saveUser(userConverter.convertToDto(testUser0));
		testUserService.saveUser(userConverter.convertToDto(testUser2));
		testUserService.saveUser(userConverter.convertToDto(testUser3));
		Integer Id0 = userRep.findByEmail(testUser0.getEmail()).getUserId();
		assertEquals("Error: wrong size of the List", 4,testUserService.getAllUsers().size());
		userRep.delete(Id0);
		assertEquals("Error: wrong size of the List", 3,testUserService.getAllUsers().size());
		
		userRep.deleteAll();
		assertEquals("Error: wrong size of the List", 0,testUserService.getAllUsers().size());
		
	}
//	testUserService.deleteUser(Id0);

	@Test
	public void testDeleteUser() {
		userRep.deleteAll();
		userRep.save(testUser0);
		userRep.save(testUser2);
		
		try {
			testUserService.deleteUser(-560);
			fail("Error: User Id is invalid (<0) but the method doesn't throw the invalidUserException");
		}catch(InvalidUserException invalidUserException) {
			assertEquals(0,0);
		}
		try {
			testUserService.deleteUser(null);
			fail("Error: User Id is invalid (null) but the method doesn't throw the invalidUserException");
		}catch(InvalidUserException invalidUserException) {
			assertEquals(0,0);
		}
		try { //Testing the method when there are no user in the repository with that Id
			assertEquals("Error: User Id is valid but it isn't in the repository and the method doesn't return false", false,testUserService.deleteUser(generateNotExistingId()));
			
		}catch (InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		User u0 = userRep.findByEmail(testUser0.getEmail());
		Integer id0 = u0.getUserId();
		try {
			assertEquals("Error: User Id is valid but the method doesn't return true", true,testUserService.deleteUser(id0));
		}catch (InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		//Checking the repository
		User u_id = userRep.findById(id0);
		User u_email = userRep.findByEmail(testUser0.getEmail());
		if(u_id != null && u_email != null) { //This is if the repository has some 
			String note = "found the same user after the delete";
			assertTrue("Error: " + note, (
							u0.getUserId().equals(u_id.getUserId()) &&
							u0.getAdmin() == u_id.getAdmin() &&
							u0.getEmail().equals(u_id.getEmail()) &&
							u0.getUserName().equals(u_id.getUserName()) &&
							u0.getPassword().equals(u_id.getPassword()) &&
							u0.getReputation().equals(u_id.getReputation())));
		}
			
		assertNull("Error: there is an user with that same id of the deleted user",u_id);
		assertNull("Error: there is an user with that same email of the deleted user",u_email);
	}
	
	@Test
	public void testLogin(){
		userRep.deleteAll();
		userRep.save(testUser);
		loginDto.setUserId(testUser.getUserId());
		String correctUserName = credentials.getUser();
		String correctPassword = credentials.getPw();
		try { //findByEmail with the email of the testUser return the testUser
			compareLogin(loginDto,testUserService.login(credentials), "Login testUser");
		}catch(InvalidLoginDataException invalidLoginDataException) {
			fail("Error: Credentials are correct but the method trows the invalidLoginDataException");
		}
		try { //findByEmail with the email of the testUser3 return null 
			testUserService.login(credentials3);
			fail("Error: login works even if the credentials aren't correct (email and password are worng)");
		}catch(InvalidLoginDataException invalidLoginDataException) {
			assertEquals(0,0);
		}
		
		credentials.setPw("WrongPassword");
		try { 
			testUserService.login(credentials);
			fail("Error: login works even if the credentials aren't correct (password is worng)");
		}catch(InvalidLoginDataException invalidLoginDataException) {
			assertEquals(0,0);
		}
		credentials.setPw(correctPassword);
		credentials.setUser("WrongUser@WrongUser.WrongUser");
		try { 
			testUserService.login(credentials);
			fail("Error: login works even if the credentials aren't correct (UserName is worng)");
		}catch(InvalidLoginDataException invalidLoginDataException) {
			assertEquals(0,0);
		}
		credentials.setUser(correctUserName);
		credentials.setPw(correctPassword);
		try { 
			compareLogin(loginDto,testUserService.login(credentials), "Login testUser");
		}catch(InvalidLoginDataException invalidLoginDataException) {
			fail("Error: Credentials are correct but the method trows the invalidLoginDataException");
		}
	
	}
	
	@Test
	public void testIncreaseUserReputation() {
		userRep.deleteAll();
		userRep.save(testUser);
		Integer id = userRep.findByEmail(testUser.getEmail()).getUserId();
		userRep.save(testUser0);
		Integer id0 = userRep.findByEmail(testUser0.getEmail()).getUserId();
		testUser2.setReputation(-5);
		userRep.save(testUser2);
		Integer id2 = userRep.findByEmail(testUser2.getEmail()).getUserId();
		testUser3.setReputation(5);
		userRep.save(testUser3);
		Integer id3 = userRep.findByEmail(testUser3.getEmail()).getUserId();
		try {
			testUserService.increaseUserReputation(null);			
			fail("Error: User Id is invalid (null) but the method doesn't throw the invalidUserException");
		}catch( InvalidUserException invalidUserException) {
			assertEquals(0,0);
		}
		try {
			testUserService.increaseUserReputation(-560);			
			fail("Error: User Id is invalid (<0) but the method doesn't throw the invalidUserException");
		}catch( InvalidUserException invalidUserException) {
			assertEquals(0,0);
		}
		try {
			assertNull("Error: User Id is valid but not in the repository and the method doesn't return null",testUserService.increaseUserReputation(generateNotExistingId()));
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		try {
			assertEquals("Error: the Reputation doesn't match", (int)userRep.findByEmail(testUser.getEmail()).getReputation()+1, (int)testUserService.increaseUserReputation(id));
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		try {
			assertEquals("Error: the Reputation doesn't match", (int)userRep.findByEmail(testUser0.getEmail()).getReputation()+1, (int)testUserService.increaseUserReputation(id0));
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		try {
			assertEquals("Error: the Reputation is at the max value and it doesn't have to increase", (int)userRep.findByEmail(testUser3.getEmail()).getReputation(), (int)testUserService.increaseUserReputation(id3));
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		try {
			assertEquals("Error: the Reputation doesn't match", (int)userRep.findByEmail(testUser2.getEmail()).getReputation()+1, (int)testUserService.increaseUserReputation(id2));
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
	}
	
	@Test
	public void testDecreaseUserReputation() {
		userRep.deleteAll();
		userRep.save(testUser);
		Integer id = userRep.findByEmail(testUser.getEmail()).getUserId();
		userRep.save(testUser0);
		Integer id0 = userRep.findByEmail(testUser0.getEmail()).getUserId();
		testUser2.setReputation(-5);
		userRep.save(testUser2);
		Integer id2 = userRep.findByEmail(testUser2.getEmail()).getUserId();
		testUser3.setReputation(5);
		userRep.save(testUser3);
		Integer id3 = userRep.findByEmail(testUser3.getEmail()).getUserId();
		try {
			testUserService.decreaseUserReputation(null);			
			fail("Error: User Id is invalid (null) but the method doesn't throw the invalidUserException");
		}catch( InvalidUserException invalidUserException) {
			assertEquals(0,0);
		}
		try {
			testUserService.decreaseUserReputation(-560);			
			fail("Error: User Id is invalid (<0) but the method doesn't throw the invalidUserException");
		}catch( InvalidUserException invalidUserException) {
			assertEquals(0,0);
		}
		try {
			assertNull("Error: User Id is valid but not in the repository and the method doesn't return null",testUserService.decreaseUserReputation(generateNotExistingId()));
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		try {
			assertEquals("Error: the Reputation doesn't match", (int)userRep.findByEmail(testUser.getEmail()).getReputation()-1, (int)testUserService.decreaseUserReputation(id));
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		try {
			assertEquals("Error: the Reputation doesn't match", (int)userRep.findByEmail(testUser0.getEmail()).getReputation()-1,(int)testUserService.decreaseUserReputation(id0));
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		try {
			assertEquals("Error: the Reputation doesn't match", (int)userRep.findByEmail(testUser3.getEmail()).getReputation()-1, (int)testUserService.decreaseUserReputation(id3));
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		try {
			assertEquals("Error: the Reputation is at the min value and it doesn't have to increase", (int)userRep.findByEmail(testUser2.getEmail()).getReputation(), (int)testUserService.decreaseUserReputation(id2));
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
	}
}// END Class
