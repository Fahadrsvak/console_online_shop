package com.app.model;

public class Employee {
@Override
	public String toString() {
		return "Employee [emailId=" + emailId + ", password=" + password + "]";
	}
private String emailId;
private String password;
public static final String employeeEmail="admin@gmail.com";
public static final String employeePassword="Admin@123";
public String getEmailId() {
	return emailId;
}
public String getPassword() {
	return password;
}
public Employee(String emailId, String password) {
	super();
	this.emailId = emailId;
	this.password = password;
}

}
