package com.cts.loan.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cts.loan.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator{

	@Override
	public Map<String, String> generateToken(User user) {
		// TODO Auto-generated method stub
		
		
		String jwtToken="";
		jwtToken = Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date()).
				signWith(SignatureAlgorithm.HS256, "secretKey").compact();
		Map<String,String> map=new HashMap<>();
		map.put("token", jwtToken);
		map.put("message", "User successfully logged in");
		
		return map;	
	}

}
