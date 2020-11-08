package com.cts.loan.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.loan.exception.UserAlreadyExistException;
import com.cts.loan.exception.UserNotFoundException;
import com.cts.loan.model.User;
import com.cts.loan.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private final UserRepository userRepo;
	
	
	

	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	public UserRepository getUserRepo() {
		return userRepo;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistException, UserNotFoundException {
		Optional<User> u1=userRepo.findById(user.getUserId());
		
		if(u1.isPresent()) {
			throw new UserAlreadyExistException("UserId already exists!");
		}
		
		userRepo.save(user);
		return true;
	}
	
	@Override
	public boolean updateUser(User user) {
		
		userRepo.save(user);
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password)
			throws  UserNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userRepo.findByUserIdAndPassword(userId, password);
		
		
		if(user==null)
		{
			throw new UserNotFoundException("userId and password mismatch");
		}
		
		return user;
	}

	@Override
	public User findByUserId(String userId) {
		User user = userRepo.findByUserId(userId);
		
		return user;
	}
	
	

}
