package com.app.services.impl;

import com.app.exception.BusinessException;
import com.app.model.Employee;
import com.app.services.EmployeeLoginService;

public class EmployeeLoginServiceImpl implements EmployeeLoginService {

	@Override
	public Employee employeeLogin(Employee employee) throws BusinessException {
		if(!employee.getEmailId().endsWith(".com")) {
			throw new BusinessException("\n----Invlaid Eamil Id-----");
		}
		if(!employee.getPassword().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}")) {
			if(employee.getPassword().matches("(?=.*[a-z]).{8,}")) {
				if(employee.getPassword().matches("(?=.*[a-z])(?=.*[A-Z]).{8,}")) {
					if(employee.getPassword().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}")) {
						if(employee.getPassword().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}")) {
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
			if(employee.getEmailId().equals(Employee.employeeEmail) && employee.getPassword().equals(Employee.employeePassword)) {
				return employee;
			}
			else {
				return null;
			}
		}
		
	}

	
	
}
