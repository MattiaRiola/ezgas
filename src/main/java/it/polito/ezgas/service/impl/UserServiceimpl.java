package it.polito.ezgas.service.impl;

import java.util.ArrayList;
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
	private UserRepository userRepo;

	private final Integer minReputation = -5;
	private final Integer maxReputation = 5;
	
	private Converter<User,UserDto> userConverter = new UserConverter();
	
	/**
	 * Default Constructor
	 */
	public UserServiceimpl() { }

	/**
	 * Constructor for Mockito
	 * @param userRepository
	 */
	public UserServiceimpl(UserRepository userRepository, Converter<User, UserDto> userConverter) {
		this.userRepo = userRepository;
		this.userConverter = userConverter;
	}
	
	
	@Override
	public UserDto getUserById(Integer userId) throws InvalidUserException {
		if(userId == null)
			throw new InvalidUserException("Invalid User id: user id is null");
		if(userId < 0)
			throw new InvalidUserException("Invalid User id: user id is < 0");
		User user = userRepo.findById(userId);
		if(user == null) {
//			System.err.println("User not found");
			return null;
		}
		return userConverter.convertToDto(user);
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		if(userDto == null)
			return null;
		
		User u = userRepo.findByEmail(userDto.getEmail());
		if(u==null) {
			User user;
			user = userRepo.save(userConverter.convertFromDto(userDto));
			return userConverter.convertToDto(user);	
		}
		//It return null if the userRepo find an user with that email
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> listUser = userRepo.findAll();
		if (listUser == null || listUser.isEmpty()) {
			return new ArrayList<>();
		}
		List<UserDto> listUserDto = new ArrayList<>();
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
//			System.err.println("User not found");
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
			if(u==null) {
//				System.err.println("User not found");
				return null;
			}
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
		if(u==null) {
//			System.err.println("User not found");
			return null;
		}
		if(u.getReputation() <= minReputation)
			return u.getReputation();

//			System.out.println("Reputation -1");
		u.setReputation(u.getReputation() - 1);
		//I've to save the change of the user's reputation in the Repository
		userRepo.save(u);
		return u.getReputation();
	}
	
}
