package com.app.dao;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Customer;

public interface CustomerVIewAllDAO {

	public List<Customer> viewAllCustomer() throws BusinessException;
	
}
