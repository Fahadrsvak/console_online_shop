package com.app.services.impl;

import com.app.dao.CustomerLoginDAO;
import com.app.dao.impl.CustomerLoginDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.services.CustomerLoginService;

public class CustomerLoginServiceImpl implements CustomerLoginService {

	private CustomerLoginDAO customerLoginDAO=new CustomerLoginDAOImpl();
	@Override
	public Customer customerLogin(Customer customer) throws BusinessException {
		if(!customer.getEmailId().endsWith(".com")) {
			throw new BusinessException("\n----Invlaid Eamil Id-----");
		}
		if(!customer.getPassword().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}")) {
			if(customer.getPassword().matches("(?=.*[a-z]).{8,}")) {
				if(customer.getPassword().matches("(?=.*[a-z])(?=.*[A-Z]).{8,}")) {
					if(customer.getPassword().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}")) {
						if(customer.getPassword().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}")) {
							throw new BusinessException("\n---Entered password is not valid---\n--- no white space allowed in password --- ");
						}
						else {
							throw new BusinessException("\n----Entered password is not valid-----\n ----Password should include at least one special character----");
						}
					}
					else {
						throw new BusinessException("\n----Entered password is not valid----\n----- Password should include at least one digit----");
					}
				}
				else {
					throw new BusinessException("\n---Entered password is not valid----\n--- Password should include at least one capital letter------");
				}
			}
			else {
				throw new BusinessException("\n----Entered password is not valid----\n---- Password should be at least 8 characters----");
			}
			
		}
		else {
			 customer=customerLoginDAO.customerLogin(customer);
		}
		return customer;
	}

	
	
}
