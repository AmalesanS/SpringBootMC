package com.cts.loan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.loan.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	public User findByUserIdAndPassword(String userId,String password);

	public User findByUserId(String userId);
}
