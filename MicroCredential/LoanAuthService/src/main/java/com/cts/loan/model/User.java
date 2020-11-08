package com.cts.loan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {

	@Id
	public String userId;
	
	public String name;
	public String password;
	public String state;
	public String country;
	public String email;
	public String address;
	public String pan;
	@Column(name="contact_no")
	public String contactNo;
	@Column(name="account_type")
	public String accountType;
	public String dob;
	
	@CreationTimestamp
	@Column(name="created_date")
	private Date createdDate;
	
	public User(String name, String userId, String password, String state, String country,
			String email, String address, String pan, String contactNo, String accountType, String dob,
			Date createdDate) {
		super();
		this.name = name;
		this.userId = userId;
		this.password = password;
		this.state = state;
		this.country = country;
		this.email = email;
		this.address = address;
		this.pan = pan;
		this.contactNo = contactNo;
		this.accountType = accountType;
		this.dob = dob;
		this.createdDate = createdDate;
	}


	public User() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserName(String userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPan() {
		return pan;
	}


	public void setPan(String pan) {
		this.pan = pan;
	}


	public String getContactNo() {
		return contactNo;
	}


	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
