package com.app.services;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Customer;


public interface CustomerViewAllService {
	
	public List<Customer> viewAllCustomer() throws BusinessException;
	
}
