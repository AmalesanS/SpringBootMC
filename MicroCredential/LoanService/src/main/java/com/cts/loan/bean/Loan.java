package com.cts.loan.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="loan")
public class Loan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	
	@Column(name="loan_type")
	private String loanType;
	
	@Column(name="loan_amount")
	private int loanAmount;
	
	@Column(name="rate_of_int")
	private String rateOfInt;
	
	@Column(name="tenure")
	private int tenure;
	
	@Column(name="user_id")
	private String userId;
	
	
	public Loan() {
		//super();
		// TODO Auto-generated constructor stub
	}

	public Loan(int id, String loanType, int loanAmount, String rateOfInt, int tenure, String userId) {
		super();
		this.id = id;
		this.loanType = loanType;
		this.loanAmount = loanAmount;
		this.rateOfInt = rateOfInt;
		this.tenure = tenure;
		this.userId = userId;
	}

	public Loan( String loanType, int loanAmount, String rateOfInt, int tenure, String userId) {
		super();
		this.loanType = loanType;
		this.loanAmount = loanAmount;
		this.rateOfInt = rateOfInt;
		this.tenure = tenure;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getRateOfInt() {
		return rateOfInt;
	}
	
	public void setRateOfInt(String rateOfInt) {
		this.rateOfInt = rateOfInt;
	}

	public int getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
