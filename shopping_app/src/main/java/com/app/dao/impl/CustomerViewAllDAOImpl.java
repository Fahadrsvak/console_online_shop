package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.CustomerVIewAllDAO;
import com.app.dao.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.Customer;


public class CustomerViewAllDAOImpl  implements CustomerVIewAllDAO{

	@Override
	public List<Customer> viewAllCustomer() throws BusinessException {
		List<Customer> customertList=new ArrayList<>();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select cu_id,cu_fName,cu_lName,cu_email from customer";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Customer customer=new Customer();
				customer.setId(resultSet.getInt("cu_id"));
				customer.setFirstName(resultSet.getString("cu_fname"));
				customer.setLastName(resultSet.getString("cu_lname"));
				customer.setEmailId(resultSet.getString("cu_email"));
				customertList.add(customer);
			}
			
			if(customertList.size()==0) {
				throw new BusinessException("No Products added try adding products");
			}
		} catch (ClassNotFoundException | SQLException e) {
			//log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return customertList;
	}

}
