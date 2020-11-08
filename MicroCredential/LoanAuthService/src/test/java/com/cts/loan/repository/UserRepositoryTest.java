package com.cts.loan.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.loan.model.User;
import com.cts.loan.repositories.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepo;
	
	private User user;
	
	
	
	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Before
	public void setUp() throws Exception{
		user = new User("Amalesan", "Amal", "P@ssw0rd", "Tamil Nadu", "India", "amal@yahoo.com", "1-89,Tanjavur",
				"AQIPA7679", "9790535878", "Savings", "07/12/1986", new Date());
		
	}
	
	@Test
	public void testRegisteredUserSuccess() {
		userRepo.save(user);
		Optional<User> obj = userRepo.findById(user.getUserId());
		assertThat(obj.equals(user));
	}
	
	
}
