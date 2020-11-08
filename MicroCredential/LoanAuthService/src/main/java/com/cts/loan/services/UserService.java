package com.cts.loan.services;

import com.cts.loan.exception.UserAlreadyExistException;
import com.cts.loan.exception.UserNotFoundException;
import com.cts.loan.model.User;

public interface UserService {
	
	public boolean saveUser(User user) throws UserAlreadyExistException,UserNotFoundException;

	public boolean updateUser(User user);

	
	public User findByUserIdAndPassword(String userId,String password)  throws UserAlreadyExistException,UserNotFoundException;

	public User findByUserId(String userId);

}
