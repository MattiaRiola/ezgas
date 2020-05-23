package it.polito.ezgas;

import it.polito.ezgas.converter.Converter;
import it.polito.ezgas.converter.impl.UserConverter;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UserConverterTest {
    @Test
    public void testAdminConvertToDto() {
        Converter<User, UserDto> converter = new UserConverter();
        User user = new User("admin", "adminpw", "admin@ezgas.com", 5);
        user.setAdmin(true);
        user.setUserId(1);
        UserDto userDto = converter.convertToDto(user);
        assertEquals((Integer)1, userDto.getUserId(), "admin dto userdId is wrong");
        assertEquals("admin", userDto.getUserName(), "admin dto username is wrong");
        assertEquals("adminpw", userDto.getPassword(), "admin dto password is wrong");
        assertEquals("admin@ezgas.com", userDto.getEmail(), "admin dto email is wrong");
        assertEquals((Integer)5, userDto.getReputation(), "admin dto reputation is wrong");
        assertTrue(userDto.getAdmin(), "admin dto admin is wrong");
    }

    @Test
    public void testFirstConstructorAdminConvertFromDto() {
        Converter<User, UserDto> converter = new UserConverter();
        UserDto userDto = new UserDto(1, "admin", "adminpw", "admin@ezgas.com", 5);
        userDto.setAdmin(true);
        User user = converter.convertFromDto(userDto);
        assertEquals("admin", user.getUserName(), "admin username is wrong");
        assertEquals("adminpw", user.getPassword(), "admin password is wrong");
        assertEquals("admin@ezgas.com", user.getEmail(), "admin email is wrong");
        assertEquals((Integer)5, user.getReputation(), "admin reputation is wrong");
        assertTrue(user.getAdmin(), "admin admin is wrong");
    }

    @Test
    public void testSecondConstructorAdminConvertFromDto() {
        Converter<User, UserDto> converter = new UserConverter();
        UserDto userDto = new UserDto(1, "admin", "adminpw", "admin@ezgas.com", 5, true);
        User user = converter.convertFromDto(userDto);
        assertEquals("admin", user.getUserName(), "admin username is wrong");
        assertEquals("adminpw", user.getPassword(), "admin password is wrong");
        assertEquals("admin@ezgas.com", user.getEmail(), "admin email is wrong");
        assertEquals((Integer)5, user.getReputation(), "admin reputation is wrong");
        assertTrue(user.getAdmin(), "admin admin is wrong");
    }

    @Test
    public void testUserConvertToDto() {
        Converter<User, UserDto> converter = new UserConverter();
        User user = new User("deadpool", "diehard", "deadpool@marvel.com", -3);
        user.setAdmin(false);
        user.setUserId(10);
        UserDto userDto = converter.convertToDto(user);
        assertEquals((Integer)10, userDto.getUserId(), "user dto userdId is wrong");
        assertEquals("deadpool", userDto.getUserName(), "user dto username is wrong");
        assertEquals("diehard", userDto.getPassword(), "user dto password is wrong");
        assertEquals("deadpool@marvel.com", userDto.getEmail(), "user dto email is wrong");
        assertEquals((Integer)(-3), userDto.getReputation(), "user dto reputation is wrong");
        assertFalse(userDto.getAdmin(), "user dto admin is wrong");
    }

    @Test
    public void testFirstConstructorUserConvertFromDto() {
        Converter<User, UserDto> converter = new UserConverter();
        UserDto userDto = new UserDto(14, "Alter Bridge", "metalingus", "ab@rock.com", 4);
        userDto.setAdmin(false);
        User user = converter.convertFromDto(userDto);
        assertEquals("Alter Bridge", user.getUserName(), "user username is wrong");
        assertEquals("metalingus", user.getPassword(), "user password is wrong");
        assertEquals("ab@rock.com", user.getEmail(), "user email is wrong");
        assertEquals((Integer)4, user.getReputation(), "user reputation is wrong");
        assertFalse(user.getAdmin(), "user admin is wrong");
    }

    @Test
    public void testSecondConstructorUserConvertFromDto() {
        Converter<User, UserDto> converter = new UserConverter();
        UserDto userDto = new UserDto(1, "Jebedaiah Kerman", "MunCrash", "jeb@ksp.com", 0, false);
        User user = converter.convertFromDto(userDto);
        assertEquals("Jebedaiah Kerman", user.getUserName(), "user username is wrong");
        assertEquals("MunCrash", user.getPassword(), "user password is wrong");
        assertEquals("jeb@ksp.com", user.getEmail(), "user email is wrong");
        assertEquals((Integer)0, user.getReputation(), "user reputation is wrong");
        assertFalse(user.getAdmin(), "user admin is wrong");
    }
}
