package com.cts.loan.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class jwtFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stubletRequ
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String authHeader = req.getHeader("authorization");
		
		if("OPTIONS".equals(req.getMethod())) {
			res.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(request, response);
		}else {
			if(authHeader==null || !authHeader.startsWith("Bearer ")) {
				throw new ServletException("Invalid authorization header");
			}
				
				String token = authHeader.substring(7);
				Claims claims = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();
				req.setAttribute("claims", claims);
				chain.doFilter(request, response);
			}
		}
		
		
		
		
		
		
		
		
		
		
	}
