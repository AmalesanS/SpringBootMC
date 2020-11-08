package com.cts.loan.conroller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.loan.controller.UserController;
import com.cts.loan.model.User;
import com.cts.loan.services.SecurityTokenGenerator;
import com.cts.loan.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private SecurityTokenGenerator securityTokenGenertor;
	
	@InjectMocks
	UserController userController;
	
	private User user;

	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		user = new User("Amalesan", "Amal", "P@ssw0rd", "Tamil Nadu", "India", "amal@yahoo.com", "1-89,Tanjavur",
				"AQIPA7679", "9790535878", "Savings", "07/12/1986", new Date());
	}
	
	@Test
	public void testRegisteredUser() throws Exception{
		when(userService.saveUser(user)).thenReturn(true);
		mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(convertJsonToString(user))).andExpect(status().isCreated())
		.andDo(print());
		verify(userService,times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(userService);
		
	}
	
	
	@Test
	public void testLoginUser() throws Exception{
		
		String userId="Amal";
		String password="P@ssw0rd";
		when(userService.saveUser(user)).thenReturn(true);
		when(userService.findByUserIdAndPassword(userId, password)).thenReturn(user);
		mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(convertJsonToString(user))).andExpect(status().isOk());
		verify(userService,times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
		verifyNoMoreInteractions(userService);
	
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
