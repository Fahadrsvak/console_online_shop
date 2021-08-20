package com.app.dao;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Customer;

public interface CustomerVIewAllDAO {

	public List<Customer> viewAllCustomer() throws BusinessException;
	public Customer searchCustomerByEmail(String email) throws BusinessException;
	public Customer searchCustomerById(int id) throws BusinessException;
	public Customer searchCustomerByOrderId(int orderId) throws BusinessException;
	public List<Customer> searchCustomerByName(String name) throws BusinessException;
	
}
