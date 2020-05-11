package it.polito.ezgas.converter.impl;

import it.polito.ezgas.converter.Converter;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convertToDto(User user) {
        if(user == null) {
            System.out.println("Conversion from user null to userDto");
            return null;
        }
        UserDto dto = new UserDto(user.getUserId(), user.getUserName(), user.getPassword(), user.getEmail(), user.getReputation(), user.getAdmin());
        return dto;
    }

    @Override
    public User convertFromDto(UserDto userDto) {
        if(userDto == null) {
            System.out.println("Conversion from userDto null to user");
            return null;
        }
        User user = new User(userDto.getUserName(), userDto.getPassword(), userDto.getEmail(), userDto.getReputation(), userDto.getAdmin());
        return user;
    }
}
