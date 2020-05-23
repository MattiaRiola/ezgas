package it.polito.ezgas;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import it.polito.ezgas.converter.Converter;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.UserService;
import it.polito.ezgas.service.impl.UserServiceimpl;


public class UserServiceTest {
	
	UserRepository userRep;
	Converter<User,UserDto> converter;

	private List<User> testUserList ;
	private User testUser;
	private UserDto testUserDto;
	private UserService testUserService;
	
	@BeforeEach
	public void setUp() {
		
		userRep = mock(UserRepository.class);
		converter = mock(Converter.class);
		testUserList = new ArrayList<User>();
		testUser = new User();
		testUserDto = new UserDto();
		testUserService = new UserServiceimpl(userRep,converter);
		/*Converter*/
		when(converter.convertToDto(testUser)).thenReturn(testUserDto);
		when(converter.convertFromDto(testUserDto)).thenReturn(testUser);
		/*Repository*/
		when(userRep.findById(6)).thenReturn(testUser);
		when(userRep.findAll()).thenReturn(testUserList);
		
	}

	@Test
	void testGetAllUsers() {
		assertEquals(testUserService.getAllUsers().size(),0,"Error: not empty List");
		testUserList.add(testUser);
		assertEquals(testUserService.getAllUsers().size(),1,"Error: wrong size of the List");
		
	}
	
	
	
}
//	/* Try/catch idiom : https://github.com/junit-team/junit4/wiki/Exception-testing */
//try {
//  list.getById(-5);
//  fail("Expected an IndexOutOfBoundsException to be thrown");
//} catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
//  assertEqual(0,0);
//}