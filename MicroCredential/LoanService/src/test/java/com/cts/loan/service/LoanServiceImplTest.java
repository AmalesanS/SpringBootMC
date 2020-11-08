package com.cts.loan.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.cts.loan.bean.Loan;
import com.cts.loan.exception.LoanAlreadyExistException;
import com.cts.loan.exception.LoanNotFoundException;
import com.cts.loan.repository.LoanRepository;
import com.cts.loan.service.LoanServiceImpl;


public class LoanServiceImplTest{
	
	transient Loan loan;
	
	@Mock
	LoanRepository loanRepository;
	
	@InjectMocks
	LoanServiceImpl loanServiceImpl;
	
	Optional<Loan> options;
	

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		loan = new Loan(1,"Personal Loan", 10000, "11%", 10 ,"Anbu");
		options = Optional.of(loan);
	}
	
	
	@Test
	public void testMockCreation() {
		assertNotNull("jpaRepository creation fails: use @injectMocks on loanserviceImpl", loan);
	}
	
	@Test
	public void testSaveLoanSuccess() throws LoanAlreadyExistException {
		when(loanRepository.save(loan)).thenReturn(loan);
		boolean flag = loanServiceImpl.saveLoan(loan);
		assertTrue("Saving loan failed.call to loanserviceimpl is false", flag);
		verify(loanRepository,times(1)).save(loan);
	}
	
	@Test(expected=LoanAlreadyExistException.class)
	public void testSaveLoanFailure() throws LoanAlreadyExistException {
		when(loanRepository.findById(1)).thenReturn(options);
		when(loanRepository.save(loan)).thenReturn(loan);
		boolean flag = loanServiceImpl.saveLoan(loan);
		assertFalse("Saving loan failed", flag);
		verify(loanRepository,times(1)).findById(loan.getId());
		verify(loanRepository,times(1)).save(loan);
	}
	

	@Test
	public void testUpdateLoan() throws LoanNotFoundException {
		when(loanRepository.findById(1)).thenReturn(options);
		when(loanRepository.save(loan)).thenReturn(loan);
		loan.setLoanAmount(10000);
		Loan loan1 = loanServiceImpl.updateLoan(loan);
		assertEquals("Loan update failed", 10000, loan1.getLoanAmount());;
		verify(loanRepository,times(1)).findById(loan.getId());
		verify(loanRepository,times(1)).save(loan);
		
	}

	@Test
	public void testGetLoanById() throws LoanNotFoundException {
		
		when(loanRepository.findById(1)).thenReturn(options);
		Loan loan1 = loanServiceImpl.getLoanById(1);
		assertEquals("fetching loan by Id failed", loan, loan1);
		verify(loanRepository,times(1)).findById(loan.getId());
		
	}

	@Test
	public void testGetAllLoans() {
		List<Loan> loanList = new ArrayList();
		when(loanRepository.findByUserId("Amal")).thenReturn(loanList);
		List<Loan> loanList1 = loanServiceImpl.getAllLoans("Amal");
		assertEquals(loanList, loanList1);
		verify(loanRepository,times(1)).findByUserId("Amal");

	}

}
