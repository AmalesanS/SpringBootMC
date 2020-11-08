package com.cts.loan.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cts.loan.exception.UserNotFoundException;
import com.cts.loan.model.User;
import com.cts.loan.services.SecurityTokenGenerator;
import com.cts.loan.services.UserService;

@RestController
@EnableWebMvc
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityTokenGenerator securityTokenGenerator;
	
	
	
	
	public SecurityTokenGenerator getSecurityTokenGenerator() {
		return securityTokenGenerator;
	}

	public void setSecurityTokenGenerator(SecurityTokenGenerator securityTokenGenerator) {
		this.securityTokenGenerator = securityTokenGenerator;
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		try {
			userService.saveUser(user);
			return new ResponseEntity<String>("User successfully created!",HttpStatus.CREATED);
			
		}
		catch(Exception ex) {
			return new ResponseEntity<String>("{\"message\":\""+ex.getMessage()+"\"}",HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody User user){
		try {
			userService.updateUser(user);
			
			return new ResponseEntity<String>("User successfully updated!",HttpStatus.ACCEPTED);
			
		}
		catch(Exception ex) {
			return new ResponseEntity<String>("{\"message\":\""+ex.getMessage()+"\"}",HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping("/getUser/{userId}")
	public ResponseEntity<?> registerUser(@PathVariable String userId){
		User user = null;
		
		try {
			user = userService.findByUserId(userId);
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<String>("{\"message\":\""+ex.getMessage()+"\"}",HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User loginUser){
		try {
			String userId = loginUser.getUserId();
			String password = loginUser.getPassword();
			
			if(userId==null || password==null) {
				throw new Exception("userId or password cannot be null");
			}
			
			User user = userService.findByUserIdAndPassword(userId, password);
			
			if(user==null)
				throw new UserNotFoundException("user does not exist");
			
			
			String pwd = user.getPassword();
			
			if(!password.equals(pwd))
				throw new Exception("Invalid login credentials. Please check password");

			Map<String,String> map=securityTokenGenerator.generateToken(user);
			
			return new  ResponseEntity<Map<String,String>>(map,HttpStatus.OK); 
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>("{\"message\":\""+ex.getMessage()+"\"}",HttpStatus.UNAUTHORIZED);
		}
		
	}
}
