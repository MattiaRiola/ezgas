package it.polito.ezgas;

import it.polito.ezgas.converter.Converter;
import it.polito.ezgas.converter.impl.UserConverter;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import org.junit.Test;
import static org.junit.Assert.*;


public class UserConverterTest {
    @Test
    public void testAdminConvertToDto() {
        Converter<User, UserDto> converter = new UserConverter();
        User user = new User("admin", "adminpw", "admin@ezgas.com", 5);
        user.setAdmin(true);
        user.setUserId(1);
        UserDto userDto = converter.convertToDto(user);
        assertEquals("admin dto userdId is wrong", (Integer)1, userDto.getUserId());
        assertEquals("admin dto username is wrong", "admin", userDto.getUserName());
        assertEquals("admin dto password is wrong", "adminpw", userDto.getPassword());
        assertEquals("admin dto email is wrong", "admin@ezgas.com", userDto.getEmail());
        assertEquals("admin dto reputation is wrong", (Integer)5, userDto.getReputation());
        assertTrue("admin dto admin is wrong", userDto.getAdmin());
    }

    @Test
    public void testFirstConstructorAdminConvertFromDto() {
        Converter<User, UserDto> converter = new UserConverter();
        UserDto userDto = new UserDto(1, "admin", "adminpw", "admin@ezgas.com", 5);
        userDto.setAdmin(true);
        User user = converter.convertFromDto(userDto);
        assertEquals("admin username is wrong", "admin", user.getUserName());
        assertEquals("admin password is wrong", "adminpw", user.getPassword());
        assertEquals( "admin email is wrong", "admin@ezgas.com", user.getEmail());
        assertEquals( "admin reputation is wrong", (Integer)5, user.getReputation());
        assertTrue("admin admin is wrong", user.getAdmin());
    }

    @Test
    public void testSecondConstructorAdminConvertFromDto() {
        Converter<User, UserDto> converter = new UserConverter();
        UserDto userDto = new UserDto(1, "admin", "adminpw", "admin@ezgas.com", 5, true);
        User user = converter.convertFromDto(userDto);
        assertEquals( "admin username is wrong", "admin", user.getUserName());
        assertEquals( "admin password is wrong", "adminpw", user.getPassword());
        assertEquals( "admin email is wrong", "admin@ezgas.com", user.getEmail());
        assertEquals( "admin reputation is wrong", (Integer)5, user.getReputation());
        assertTrue( "admin admin is wrong", user.getAdmin());
    }

    @Test
    public void testUserConvertToDto() {
        Converter<User, UserDto> converter = new UserConverter();
        User user = new User("deadpool", "diehard", "deadpool@marvel.com", -3);
        user.setAdmin(false);
        user.setUserId(10);
        UserDto userDto = converter.convertToDto(user);
        assertEquals("user dto userdId is wrong", (Integer)10, userDto.getUserId());
        assertEquals("user dto username is wrong", "deadpool", userDto.getUserName());
        assertEquals("user dto password is wrong", "diehard", userDto.getPassword());
        assertEquals("user dto email is wrong", "deadpool@marvel.com", userDto.getEmail());
        assertEquals("user dto reputation is wrong", (Integer)(-3), userDto.getReputation());
        assertFalse("user dto admin is wrong", userDto.getAdmin());
    }

    @Test
    public void testFirstConstructorUserConvertFromDto() {
        Converter<User, UserDto> converter = new UserConverter();
        UserDto userDto = new UserDto(14, "Alter Bridge", "metalingus", "ab@rock.com", 4);
        userDto.setAdmin(false);
        User user = converter.convertFromDto(userDto);
        assertEquals("user username is wrong", "Alter Bridge", user.getUserName());
        assertEquals("user password is wrong", "metalingus", user.getPassword());
        assertEquals("user email is wrong", "ab@rock.com", user.getEmail());
        assertEquals( "user reputation is wrong", (Integer)4, user.getReputation());
        assertFalse("user admin is wrong", user.getAdmin());
    }

    @Test
    public void testSecondConstructorUserConvertFromDto() {
        Converter<User, UserDto> converter = new UserConverter();
        UserDto userDto = new UserDto(1, "Jebedaiah Kerman", "MunCrash", "jeb@ksp.com", 0, false);
        User user = converter.convertFromDto(userDto);
        assertEquals("user username is wrong", "Jebedaiah Kerman", user.getUserName());
        assertEquals("user password is wrong", "MunCrash", user.getPassword());
        assertEquals( "user email is wrong", "jeb@ksp.com", user.getEmail());
        assertEquals("user reputation is wrong", (Integer)0, user.getReputation());
        assertFalse("user admin is wrong", user.getAdmin());
    }
}
