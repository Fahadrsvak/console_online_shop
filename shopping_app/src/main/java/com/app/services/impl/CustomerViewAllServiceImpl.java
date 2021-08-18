package com.app.services.impl;

import java.util.List;

import com.app.dao.CustomerVIewAllDAO;
import com.app.dao.impl.CustomerViewAllDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.services.CustomerViewAllService;

public class CustomerViewAllServiceImpl implements CustomerViewAllService{
	
	private CustomerVIewAllDAO customerViewAllDAO=new CustomerViewAllDAOImpl();
	@Override
	public List<Customer> viewAllCustomer() throws BusinessException {
		
		return customerViewAllDAO.viewAllCustomer();
	}

}
