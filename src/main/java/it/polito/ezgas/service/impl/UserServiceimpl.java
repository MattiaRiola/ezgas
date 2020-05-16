package it.polito.ezgas.service.impl;

import java.util.LinkedList;
import java.util.List;

import it.polito.ezgas.converter.Converter;
import it.polito.ezgas.converter.impl.UserConverter;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.service.UserService;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepository userRepo;

	private final Integer minReputation = -5;
	private final Integer maxReputation = 5;

	/**
	 * Default Constructor
	 */
	public UserServiceimpl() { }
	/**
	 * Constructor for Mockito
	 * @param userRepository
	 */
	public UserServiceimpl(UserRepository userRepository) {
		this.userRepo = userRepository;
	}
	
	@Override
	public UserDto getUserById(Integer userId) throws InvalidUserException {
		if(userId == null)
			throw new InvalidUserException("Invalid User id: user id is null");
		if(userId < 0)
			throw new InvalidUserException("Invalid User id: user id is < 0");
		User user = userRepo.findById(userId);
		if(user == null)
			throw new InvalidUserException("Invalid User id: user id is not found");
		Converter<User, UserDto> userConverter = new UserConverter();
		return userConverter.convertToDto(user);
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		if(userDto == null)
			return null;
		Converter<User, UserDto> userConverter = new UserConverter();
		//TODO ? do we have to handle the case when an user try to register with an existing email?
		User u = userRepo.findByEmail(userDto.getEmail());
		if(u != null) {
			return null; // should it return null in that case?
		}

		User user = userRepo.save(userConverter.convertFromDto(userDto));
		return userConverter.convertToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		Converter<User, UserDto> userConverter = new UserConverter();
		List<User> listUser = userRepo.findAll();
		if (listUser == null) {
			return new LinkedList<>();
		}
		List<UserDto> listUserDto = new LinkedList<>();
		listUser.forEach( u -> {
								UserDto uDto = userConverter.convertToDto(u);
								listUserDto.add(uDto);
								});
		return listUserDto;
	}

	@Override
	public Boolean deleteUser(Integer userId) throws InvalidUserException {
		if(userId == null)
			throw new InvalidUserException("Invalid User id: user id is null");
		if(userId < 0)
			throw new InvalidUserException("Invalid User id: user id is < 0");

		User user = userRepo.findById(userId);
		if(user == null){
			//There aren't Users with that userId
			return false;
		}

		userRepo.delete(userId);
		return true;
	}

	@Override
	public LoginDto login(IdPw credentials) throws InvalidLoginDataException {
		User u = userRepo.findByEmail(credentials.getUser());
		if(u == null)
			throw new InvalidLoginDataException("Wrong Username (email)");
		if(u.getPassword().compareTo(credentials.getPw()) != 0)
			throw new InvalidLoginDataException("Wrong password");

		// TODO Implement the Token
		LoginDto loginDto = new LoginDto
							(u.getUserId(),u.getUserName(),"",
							u.getEmail(), u.getReputation());
		loginDto.setAdmin(u.getAdmin());
		return loginDto;
	}

		@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
			if(userId == null)
				throw new InvalidUserException("Invalid User id: user id is null");
			if(userId < 0)
				throw new InvalidUserException("Invalid User id: user id is < 0");

			User u = userRepo.findById(userId);
			if(u.getReputation() >= maxReputation) //if the user reached the max reputation
				return u.getReputation();
//			System.out.println("Reputation +1");
			u.setReputation(u.getReputation() + 1);
			//I've to save the change of the user's reputation in the Repository
			userRepo.save(u);
			return u.getReputation();
	}

	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		if(userId == null)
			throw new InvalidUserException("Invalid User id: user id is null");
		if(userId < 0)
			throw new InvalidUserException("Invalid User id: user id is < 0");


		User u = userRepo.findById(userId);
		if(u.getReputation() <= minReputation)
			return u.getReputation();

//			System.out.println("Reputation -1");
		u.setReputation(u.getReputation() - 1);
		//I've to save the change of the user's reputation in the Repository
		userRepo.save(u);
		return u.getReputation();
	}
	
}
