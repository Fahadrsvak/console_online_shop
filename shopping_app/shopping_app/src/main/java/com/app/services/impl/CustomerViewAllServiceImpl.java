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
	@Override
	public Customer searchCustomerByEmail(String email) throws BusinessException {
		Customer customer=null;
		if(!email.endsWith(".com")) {
			throw new BusinessException("\n----Invlaid Eamil Id-----");
		}else {
			customer = customerViewAllDAO.searchCustomerByEmail(email);
		}
		return customer;
	}
	@Override
	public Customer searchCustomerById(int id) throws BusinessException {
		Customer customer=null;
		if(id>100 ||id<0) {
			throw new BusinessException("Invalid customer Id "+id);
		}else {
			//code here to DAO
			customer=customerViewAllDAO.searchCustomerById(id);
			
		}
		return customer;
	}
	@Override
	public Customer searchCustomerByOrderId(int orderId) throws BusinessException {
		Customer customer=null;
		customer=customerViewAllDAO.searchCustomerByOrderId(orderId);
		return customer;
	}
	@Override
	public List<Customer> searchCustomerByName(String name) throws BusinessException {
		// TODO Auto-generated method stub
		return customerViewAllDAO.searchCustomerByName(name);
	}


}
