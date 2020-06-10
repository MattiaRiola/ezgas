package it.polito.ezgas;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import it.polito.ezgas.converter.Converter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.UserService;
import it.polito.ezgas.service.impl.UserServiceimpl;


public class UserServiceTestMock {
	
	private UserRepository userRep;
	private Converter<User,UserDto> converter;

	
	
	private List<User> testUserList ;
	
	private User testUser;
	private User testUser0;
	private User testUser2;
	private User testUser3;
	private UserDto testUserDto;
	private UserDto testUserDto0;
	private UserDto testUserDto2;
	private UserDto testUserDto3;
	
	private IdPw credentials;
	private IdPw credentials0;
	private IdPw credentials2;
	private IdPw credentials3;
	
	private LoginDto loginDto;
	private LoginDto loginDto0;
	private LoginDto loginDto2;
	private LoginDto loginDto3;
	
	private UserService testUserService;
	
	private void compareUsers(User expected,User actual, String note) {
		assertEquals(expected.getUserId(),actual.getUserId(),"Error - " + note + ": user id is wrong");
		assertEquals(expected.getAdmin(),actual.getAdmin(),"Error - "+ note + " : user admin is wrong");
		assertEquals(expected.getEmail(),actual.getEmail(),"Error - "+ note + " : user Email is wrong");
		assertEquals(expected.getUserName(),actual.getUserName(),"Error - "+ note + " : user UserName is wrong");
		assertEquals(expected.getPassword(),actual.getPassword(),"Error -" + note + " : user password is wrong");
		assertEquals(expected.getReputation(),actual.getReputation(),"Error - " + note + " : user reputation is wrong");
	}
	private void compareUsers(UserDto expected,UserDto actual,String note) {
		assertEquals(expected.getUserId(),actual.getUserId(),"Error - " + note + ": user id is wrong");
		assertEquals(expected.getAdmin(),actual.getAdmin(),"Error - "+ note + " : user admin is wrong");
		assertEquals(expected.getEmail(),actual.getEmail(),"Error - "+ note + " : user Email is wrong");
		assertEquals(expected.getUserName(),actual.getUserName(),"Error - "+ note + " : user UserName is wrong");
		assertEquals(expected.getPassword(),actual.getPassword(),"Error -" + note + " : user password is wrong");
		assertEquals(expected.getReputation(),actual.getReputation(),"Error - " + note + " : user reputation is wrong");
	}
	private void compareLogin(LoginDto expected, LoginDto actual,String note) {
		assertEquals(expected.getUserId(),actual.getUserId(),"Error - " + note + ": user id is wrong");
		assertEquals(expected.getAdmin(),actual.getAdmin(),"Error - "+ note + " : user admin is wrong");
		assertEquals(expected.getEmail(),actual.getEmail(),"Error - "+ note + " : user Email is wrong");
		assertEquals(expected.getUserName(),actual.getUserName(),"Error - "+ note + " : user UserName is wrong");
		assertEquals(expected.getReputation(),actual.getReputation(),"Error - " + note + " : user reputation is wrong");
//		assertEquals(expected.getToken(),actual.getToken(),"Error - " + note + " : user token is wrong");
	}
	
	
	
	
	@BeforeEach
	public void setUp() {
		
		userRep = mock(UserRepository.class);
		converter = mock(Converter.class);
		
		testUserList = new ArrayList<User>();
		
		testUser = new User("test","password","test@test.test",-3);
		testUser.setUserId(6);
		testUser.setAdmin(true);
		testUserDto = new UserDto(6,"test","password","test@test.test",-3);
		testUserDto.setAdmin(true);
		
		testUser0 = new User("test0","password0","test0@test0.test0",0);
		testUser0.setUserId(0);
		testUser0.setAdmin(false);
		testUserDto0 = new UserDto(0,"test0","password0","test0@test0.test0",0);
		testUserDto0.setAdmin(false);
		
		testUser2 = new User("test2","password2","test2@test2.test2",2);
		testUser2.setUserId(2);
		testUser2.setAdmin(false);
		testUserDto2 = new UserDto(2,"test2","password2","test2@test2.test2",2);
		testUserDto2.setAdmin(false);
		
		testUser3 = new User("test3","password3","test3@test3.test3",3);
		testUser3.setUserId(3);
		testUser3.setAdmin(false);
		testUserDto3 = new UserDto(3,"test3","password3","test3@test3.test3",3);
		testUserDto3.setAdmin(false);
		
		credentials = new IdPw("test@test.test","password");
		credentials0 = new IdPw("test0@test0.test0","password0");
		credentials2 = new IdPw("tes2t@test2.test2","password2");
		credentials3 = new IdPw("test3@test3.test3","password3");
		
		loginDto = new LoginDto(6,"test","","test@test.test",-3);
		loginDto.setAdmin(true);
		loginDto0 = new LoginDto(0,"test0","","test0@test0.test0",0);
		loginDto0.setAdmin(false);
		loginDto2 = new LoginDto(2,"test2","","test2@test2.test2",2);
		loginDto2.setAdmin(false);
		loginDto3 = new LoginDto(3,"test3","","test3@test3.test3",3);
		loginDto3.setAdmin(false);
		

		testUserService = new UserServiceimpl(userRep,converter);
		
		/*Converter*/
		when(converter.convertToDto(testUser)).thenReturn(testUserDto);
		when(converter.convertToDto(testUser0)).thenReturn(testUserDto0);
		when(converter.convertToDto(testUser2)).thenReturn(testUserDto2);
		when(converter.convertToDto(testUser3)).thenReturn(testUserDto3);
		
		when(converter.convertFromDto(testUserDto)).thenReturn(testUser);
		when(converter.convertFromDto(testUserDto0)).thenReturn(testUser0);
		when(converter.convertFromDto(testUserDto2)).thenReturn(testUser2);
		when(converter.convertFromDto(testUserDto3)).thenReturn(testUser3);
		
		
		/*Repository*/
		when(userRep.findById(6)).thenReturn(testUser);
		when(userRep.findById(0)).thenReturn(testUser0);
		when(userRep.findById(3)).thenReturn(null);
		when(userRep.findById(120)).thenReturn(null);
		
		when(userRep.findAll()).thenReturn(testUserList);
		
		when(userRep.findByEmail("test@test.test")).thenReturn(testUser); //"present in the repository"
		when(userRep.findByEmail("test3@test3.test3")).thenReturn(null); //"not present in the repository"
		when(userRep.findByEmail("WrongUser@WrongUser.WrongUser")).thenReturn(null); //"not present in the repository"
		
		when(userRep.save(testUser0)).thenReturn(testUser0);
		when(userRep.save(testUser)).thenReturn(testUser);
		when(userRep.save(testUser2)).thenReturn(testUser2);
		when(userRep.save(testUser3)).thenReturn(testUser3);

		
	}

	
	
	@Test
	public void testgetUserById() {
		testUserList.add(testUser);
		testUserList.add(testUser0);
		testUserList.add(testUser2);
		testUserList.add(testUser3);
		try {
			compareUsers(testUserDto,testUserService.getUserById(6),"search UserById 6");
		}catch( InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws an invalidUserException");
		}
		try {
			compareUsers(testUserDto0,testUserService.getUserById(0),"search UserById 0");
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
		try {
			assertNull("Error: User id isn't in the repository but the method doesn't return null",testUserService.getUserById(120));
		}catch( InvalidUserException invalidUserException) {
			fail("Error: User Id is valid and not found in the repository but the method throws the invalidUserException");
		}
		
		
	}
	@Test
	public void testSaveUser(){
		assertNull("Error: saved a null User and the saveUser method doesn't return null",testUserService.saveUser(null));
		testUserList.add(testUser);
		compareUsers(testUserDto0,testUserService.saveUser(testUserDto0),"saving user doesn't return the saved user");
		
	}
	@Test
	public void testGetAllUsers() {
		assertEquals(0,testUserService.getAllUsers().size(),"Error: not empty List");
		testUserList.add(testUser);
		assertEquals(1,testUserService.getAllUsers().size(),"Error: wrong size of the List");
		testUserList.add(testUser);
		testUserList.add(testUser);
		testUserList.add(testUser);
		assertEquals(4,testUserService.getAllUsers().size(),"Error: wrong size of the List");
		testUserList.remove(1);
		assertEquals(3,testUserService.getAllUsers().size(),"Error: wrong size of the List");		
		testUserList.clear();
		assertEquals(0,testUserService.getAllUsers().size(),"Error: wrong size of the List");
		
	}
		
	@Test
	public void testDeleteUser() {
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
		try { //Testing the method when the user isn't in the repository (findByEmail(test3@test3.test3) will return null)
			assertEquals(false,testUserService.deleteUser(3),"Error: User Id is valid but it isn't in the repository and the method doesn't return false");
			
		}catch (InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		try {
			assertEquals(true,testUserService.deleteUser(6),"Error: User Id is valid but the method doesn't return true");
			
		}catch (InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
	}
	
	@Test
	public void testLogin(){
		testUserList.add(testUser);
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
		testUserList.add(testUser);
		testUserList.add(testUser0);
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
			assertNull("Error: User Id is valid but not in the repository and the method doesn't return null",testUserService.increaseUserReputation(3));
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		try {
			assertEquals((int)testUser.getReputation()+1, (int)testUserService.increaseUserReputation(6),"Error: the Reputation doesn't match");
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		try {
			assertEquals((int)testUser0.getReputation()+1, (int)testUserService.increaseUserReputation(0),"Error: the Reputation doesn't match");
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		testUser.setReputation(5);
		try {
			assertEquals((int)testUser.getReputation(),(int)testUserService.increaseUserReputation(6),"Error: the Reputation is at the max value and it doesn't have to increase");
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		testUser.setReputation(-5);
		try {
			assertEquals((int)testUser.getReputation()+1,(int)testUserService.increaseUserReputation(6),"Error: the Reputation doesn't match");
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		
		
		
	}
	@Test
	public void testDcreaseUserReputation() {
		testUserList.add(testUser);
		testUserList.add(testUser0);
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
			assertNull("Error: User Id is valid but not in the repository and the method doesn't return null",testUserService.decreaseUserReputation(3));
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		try {
			assertEquals((int)testUser.getReputation()-1,(int)testUserService.decreaseUserReputation(6),"Error: the Reputation doesn't match");
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		try {
			assertEquals((int)testUser0.getReputation()-1,(int)testUserService.decreaseUserReputation(0),"Error: the Reputation doesn't match");
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		testUser.setReputation(-5);
		try {
			assertEquals((int)testUser.getReputation(),(int)testUserService.decreaseUserReputation(6),"Error: the Reputation is at the min value and it doesn't have to decrease");
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		testUser.setReputation(5);
		try {
			assertEquals((int)testUser.getReputation()-1,(int)testUserService.decreaseUserReputation(6),"Error: the Reputation doesn't match");
		} catch(InvalidUserException invalidUserException) {
			fail("Error: User Id is valid but the method throws the invalidUserException");
		}
		
		
		
	}
}
//	/* Try/catch idiom : https://github.com/junit-team/junit4/wiki/Exception-testing */
//Example:
//try {
//  list.getById(-5);
//  fail("Expected an IndexOutOfBoundsException to be thrown");
//} catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
//  assertEqual(0,0);
//}