package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.app.dao.CustomerCreateDAO;
import com.app.dao.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.Customer;

public class CustomerCreateDAOImpli implements CustomerCreateDAO {

	@Override
	public Customer customerCreate(Customer customer) throws BusinessException {
		try(Connection connection =MySqlDbConnection.getConnection()){
			String sql="insert into customer(cu_fName,cu_lName,cu_email,cu_password) values(?,?,?,?) ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());
			preparedStatement.setString(3, customer.getEmailId());
			preparedStatement.setString(4, customer.getPassword());
			
			int c =preparedStatement.executeUpdate();
			if(c==1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()) {
					customer.setId(resultSet.getInt(1));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error occured contact system admin");
		}
		
	
		return customer;
	}
	public CustomerCreateDAOImpli() {
		// TODO Auto-generated constructor stub
	}

}
