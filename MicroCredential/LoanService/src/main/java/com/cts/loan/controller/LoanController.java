package com.cts.loan.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.loan.bean.Loan;
import com.cts.loan.exception.LoanAlreadyExistException;
import com.cts.loan.exception.LoanNotFoundException;
import com.cts.loan.service.LoanService;

import io.jsonwebtoken.Jwts;

@CrossOrigin
@RestController
@RequestMapping(path="/api/v1/loanService")
public class LoanController {
	
	private LoanService loanService;
	
	@Autowired
	public LoanController(LoanService loanService) {
		this.loanService=loanService;
		
	}
	
	@PostMapping
	public ResponseEntity<?> saveNewLoan(@RequestBody Loan loan,HttpServletRequest request,HttpServletResponse response)
	{
		ResponseEntity<?> responseEntity;
		try
		{
			String authHeader=request.getHeader("authorization");
			String token=authHeader.substring(7);
			String userId=Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody().getSubject();
			loan.setUserId(userId);
			loanService.saveLoan(loan);
			responseEntity=new ResponseEntity<Loan>(loan, HttpStatus.CREATED);
			
		}catch(LoanAlreadyExistException e)
		{
			responseEntity=new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}", HttpStatus.CONFLICT);
			
		}
		return responseEntity;
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateLoan(@PathVariable  Integer id  ,@RequestBody Loan loan)
	{
		ResponseEntity<?> responseEntity;
		try
		{
			Loan retrievedLoan = loanService.updateLoan(loan);
			responseEntity=new ResponseEntity<Loan>(retrievedLoan, HttpStatus.OK);
			
		}catch(LoanNotFoundException e)
		{
			responseEntity=new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}", HttpStatus.CONFLICT);
			
		}
		return responseEntity;
	}
	
	@PostMapping(path = "/{id}")
	public ResponseEntity<?> getLoanById(@PathVariable  Integer id)
	{
		ResponseEntity<?> responseEntity;
		Loan loan = null;
		try
		{
			loan = loanService.getLoanById(id);
		
			responseEntity=new ResponseEntity<Loan>(loan, HttpStatus.OK);
			
		}catch(LoanNotFoundException e)
		{
			responseEntity=new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}", HttpStatus.CONFLICT);
			
		}
		return responseEntity;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllLoans(ServletRequest request,ServletResponse response)
	{
		ResponseEntity<?> responseEntity;
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		
		String authHeader=req.getHeader("authorization");
		String token=authHeader.substring(7);
		String userId=Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody().getSubject();
		

		List<Loan> loanList = loanService.getAllLoans(userId);

		responseEntity=new ResponseEntity<List<Loan>>(loanList, HttpStatus.OK);


		return responseEntity;
	}

}
