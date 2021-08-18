package com.app.services.impl;

import com.app.dao.CustomerCreateDAO;
import com.app.dao.impl.CustomerCreateDAOImpli;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.services.CustomerCreateService;

public class CustomerCreateServiceImpl implements CustomerCreateService {
	private CustomerCreateDAO customerCreateDAO=new CustomerCreateDAOImpli();
	@Override
	public Customer customerCreate(Customer customer) throws BusinessException {
		if(!customer.getEmailId().endsWith(".com")) {
			throw new BusinessException("\n----Invlaid Eamil Id-----");
		}
		if(!customer.getFirstName().matches("[A-Z]{1}[A-Za-z\\s\\.]{1,25}")) {
			throw new BusinessException("\n ----first name is not valid----\n---- it can only include 'A-Za-z. '\n----and  first letter should be capital----");
		}
		if(!customer.getLastName().matches("[A-Za-z]{1,10}")) {
			throw new BusinessException("\n-----last name is not valid--- \n -----it can only include A-Za-z -----");
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
			customer = customerCreateDAO.customerCreate(customer);
		}
		return customer;
	}

}
