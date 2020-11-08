package com.cts.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;

@SpringBootApplication
public class LoanServiceApplication {

	
	
	@Bean
	public FilterRegistrationBean jwtFilter(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new com.cts.loan.filter.jwtFilter());
		registrationBean.addUrlPatterns("/api/*");
		
		return registrationBean;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(LoanServiceApplication.class, args);
	}
}
