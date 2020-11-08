package com.cts.loan.service;

import java.util.List;

import com.cts.loan.bean.Loan;
import com.cts.loan.exception.LoanAlreadyExistException;
import com.cts.loan.exception.LoanNotFoundException;


public interface LoanService {
	
boolean saveLoan(Loan loan) throws LoanAlreadyExistException;

Loan updateLoan(Loan loan) throws LoanNotFoundException;

Loan getLoanById(Integer Id) throws LoanNotFoundException;

List<Loan> getAllLoans(String userId);
}
