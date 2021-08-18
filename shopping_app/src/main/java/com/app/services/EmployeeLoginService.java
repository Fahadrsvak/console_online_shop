package com.app.services;

import com.app.exception.BusinessException;
import com.app.model.Employee;


public interface EmployeeLoginService {
	public Employee employeeLogin(Employee employee) throws BusinessException;
}
 