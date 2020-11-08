package com.cts.loan.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.cts.loan.bean.Loan;
import com.cts.loan.controller.LoanController;
import com.cts.loan.service.LoanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(LoanController.class)
public class LoanControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	Loan loan;
	
	@MockBean
	private LoanService loanService;
	
	static List<Loan> loans;
	
	@Before
	public void setUp()
	{
		loans = new ArrayList<Loan>();
		loan = new Loan(1,"Home Loan", 5000, "10%", 5 ,"Amal");
		loans.add(loan);
		loan = new Loan(1,"Personal Loan", 10000, "11%", 10 ,"Anbu");
		loans.add(loan);
	}
	
	
	@Test
	public void testSaveNewLoanSuccess() throws Exception
	{
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBbWFsIiwiaWF0IjoxNTM1NDg0NDk1fQ.o8KZ6Z9o-fdCIAXOaQcAfBG6-MQK6v-ARt_WKj8vJUU";
		when(loanService.saveLoan(loan)).thenReturn(true);
		mockMvc.perform(post("/api/v1/loanService").header("authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(convertJsonToString(loan)))
		.andExpect(status().isCreated());
		verify(loanService,times(1)).saveLoan(Mockito.any(Loan.class));
		verifyNoMoreInteractions(loanService);
	}
	
	@Test
	public void testUpdateLoanSuccess() throws  Exception
	{
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBbWFsIiwiaWF0IjoxNTM1NDg0NDk1fQ.o8KZ6Z9o-fdCIAXOaQcAfBG6-MQK6v-ARt_WKj8vJUU";
		loan.setLoanAmount(10000);
		when(loanService.updateLoan(loan)).thenReturn(loans.get(0));
		mockMvc.perform(put("/api/v1/loanService/{id}",1).header("authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(convertJsonToString(loan)))
		.andExpect(status().isOk());
		verify(loanService,times(1)).updateLoan(Mockito.any(Loan.class));
		verifyNoMoreInteractions(loanService);
	}
	
	
	
	@Test
	public void testGetAllLoans() throws Exception
	{
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBbWFsIiwiaWF0IjoxNTM1NDg0NDk1fQ.o8KZ6Z9o-fdCIAXOaQcAfBG6-MQK6v-ARt_WKj8vJUU";
		when(loanService.getAllLoans("Amal")).thenReturn(loans);
		mockMvc.perform(get("/api/v1/loanService/").header("authorization","Bearer "+token))
		.andExpect(status().isOk());
		verify(loanService,times(1)).getAllLoans("Amal");
		verifyNoMoreInteractions(loanService);	
	}
	
	private static String convertJsonToString(Object object)  throws JsonProcessingException{
		String result;
		try {
			ObjectMapper mapper=new ObjectMapper();
			String jsonText = mapper.writeValueAsString(object);
			result=jsonText;
		}catch(JsonProcessingException e)
		{
			result="json processing error";
		}
		return result;
		
	}

}