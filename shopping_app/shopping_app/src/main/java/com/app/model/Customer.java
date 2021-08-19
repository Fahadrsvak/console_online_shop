package com.app.model;

public class Customer {
private int id;
private String firstName;
private String lastName;
private String password;
private String emailId;

public Customer(String email, String pass) {
	this.password = pass;
	this.emailId = email;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmailId() {
	return emailId;
}

public void setEmailId(String emailId) {
	this.emailId = emailId;
}

@Override
public String toString() {
	return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
			+ ", emailId=" + emailId + "]";
}

public String formatView() {
	return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + "]";
}

public Customer(String firstName, String lastName, String emailId, String password) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.password = password;
	this.emailId = emailId;
}

public Customer() {
	
	// TODO Auto-generated constructor stub
}



}
