package com.app.services;

import com.app.exception.BusinessException;
import com.app.model.Customer;

public interface CustomerLoginService {
	
	public Customer customerLogin(Customer customer) throws BusinessException;
}
