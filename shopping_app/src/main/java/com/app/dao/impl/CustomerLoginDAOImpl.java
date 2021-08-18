package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dao.CustomerLoginDAO;
import com.app.dao.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.Customer;


public class CustomerLoginDAOImpl implements CustomerLoginDAO{

	@Override
	public Customer customerLogin(Customer customer) throws BusinessException {
		try(Connection connection =MySqlDbConnection.getConnection()){
			String sql ="select cu_id,cu_fName,cu_lName,cu_email,cu_password from customer where cu_email=? and cu_password=?";
			//System.out.println("connected");
			PreparedStatement preparedStatement =connection.prepareStatement(sql);
			
			preparedStatement.setString(1, customer.getEmailId());
			preparedStatement.setString(2, customer.getPassword());
			ResultSet resultSet =preparedStatement.executeQuery();

			if(resultSet.next()) {
				customer.setId(resultSet.getInt("cu_id"));
				customer.setFirstName(resultSet.getString("cu_fName"));
				customer.setLastName(resultSet.getString("cu_lName"));
				customer.setEmailId(resultSet.getString("cu_email"));
				customer.setPassword(resultSet.getString("cu_password"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error occured please contact support");
		}
		return customer;
	}

}
