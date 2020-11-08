package com.cts.loan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cts.loan.bean.Loan;
import com.cts.loan.exception.LoanAlreadyExistException;
import com.cts.loan.exception.LoanNotFoundException;
import com.cts.loan.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService{
	
	@Autowired
	LoanRepository loanRepository;
	

	@Override
	public boolean saveLoan(Loan loan) throws LoanAlreadyExistException {
		// TODO Auto-generated method stub
		Optional<Loan> loanObj = loanRepository.findById(loan.getId());
		if(loanObj.isPresent())
		{
			throw new LoanAlreadyExistException(loan.getId()+" already exists");
		}
		loanRepository.save(loan);
		
		return true;
	}

	@Override
	public Loan updateLoan(Loan loan) throws LoanNotFoundException {
		Optional<Loan> loanObj = loanRepository.findById(loan.getId());
		if(!loanObj.isPresent())
		{
			throw new LoanNotFoundException(loan.getId()+" not found");
		}
		loanRepository.save(loan);
		
		return loan;
	}

	@Override
	public Loan getLoanById(Integer id) throws LoanNotFoundException {
		
		Loan loan = loanRepository.findById(id).get();
		
		if(loan == null)
			throw new LoanNotFoundException("Loan with id="+ id +" not found");
		// TODO Auto-generated method stub
		return loan;
	}

	@Override
	public List<Loan> getAllLoans(String userId) {
		// TODO Auto-generated method stub
		return loanRepository.findByUserId(userId);
	}

}
