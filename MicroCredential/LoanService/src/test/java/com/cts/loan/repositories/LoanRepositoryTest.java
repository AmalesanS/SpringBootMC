package com.cts.loan.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cts.loan.bean.Loan;
import com.cts.loan.repository.LoanRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional

public class LoanRepositoryTest{
	
	@Autowired
	LoanRepository loanRepository;
	
	Loan loan;
	
   public void setLoanRepository(LoanRepository loanRepository) {
		this.loanRepository = loanRepository;
	}

	@Test
	public void testSaveLoan(){
		loanRepository.save(new Loan("Personal Loan", 10000, "11%", 10 ,"Anbu"));
		Loan loan = loanRepository.getOne(1);
		assertThat(loan.getId()).isEqualTo(1);
	}

	@Test
	public void testUpdateLoan() {
		loanRepository.save(new Loan("Personal Loan", 10000, "11%", 10 ,"Anbu"));
		List<Loan> loanList = loanRepository.findByUserId("Anbu");
		assertEquals(loanList.get(0).getLoanAmount(), 10000);
		
		loanList.get(0).setLoanAmount(20000);
		loanRepository.save(loanList.get(0));
		List<Loan> loanList1 = loanRepository.findByUserId("Anbu");
		assertEquals(20000, loanList1.get(0).getLoanAmount());
	}

	@Test
	public void testGetLoan(){
		loanRepository.save(new Loan("Personal Loan", 10000, "11%", 10 ,"Anbu"));
		List<Loan> loanList = loanRepository.findByUserId("Anbu");
		assertEquals(loanList.get(0).getLoanAmount(), 10000);
	}

	@Test
	public void testGetAllLoans() {
		loanRepository.save(new Loan(1,"Personal Loan", 10000, "11%", 10 ,"Anbu"));
		loanRepository.save(new Loan(2,"Personal Loan", 20000, "10%", 10 ,"Anbu"));
		
		List<Loan> loanList = loanRepository.findByUserId("Anbu");
		assertEquals(loanList.get(0).getLoanAmount(), 10000);
	}

}

