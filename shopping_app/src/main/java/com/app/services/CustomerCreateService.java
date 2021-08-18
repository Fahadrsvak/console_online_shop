package com.app.services;

import com.app.exception.BusinessException;
import com.app.model.Customer;

public interface CustomerCreateService {
	public Customer customerCreate(Customer customer) throws BusinessException ;

}
