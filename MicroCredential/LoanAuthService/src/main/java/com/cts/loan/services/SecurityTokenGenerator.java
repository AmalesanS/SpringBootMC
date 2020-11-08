package com.cts.loan.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cts.loan.model.User;


public interface SecurityTokenGenerator {
	
	public Map<String,String> generateToken(User user);

}
