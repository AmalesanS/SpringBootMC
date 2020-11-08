package com.cts.loan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.loan.bean.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer>{
	
	public List<Loan> findByUserId(String userId);
}
