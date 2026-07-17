package com.example.demo.model;

public class FormContact {
	
	private String customerName;
	private String email;
	private String phone;
	private String subject;
	private String message;
	
	public FormContact() {

	}
	
	public FormContact(
			String customerName,
			String email,
			String phone,
			String subject,
			String message) {
		
		this.customerName = customerName;
		this.email = email;
		this.phone = phone;
		this.subject = subject;
		this.message = message;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(
			String customerName) {
		
		this.customerName = customerName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(
		String email) {
		
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(
			String phone) {
		this.phone = phone;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(
			String subject) {
		this.subject = subject;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(
			String message) {
		this.message = message;
	}
	
}
