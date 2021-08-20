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

	@Override
	public Customer searchCustomerByEmail(String email) throws BusinessException {
		Customer customer=new Customer();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select cu_id,cu_fName,cu_lName,cu_email from customer where cu_email=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer.setId(resultSet.getInt("cu_id"));
				customer.setFirstName(resultSet.getString("cu_fname"));
				customer.setLastName(resultSet.getString("cu_lname"));
				customer.setEmailId(resultSet.getString("cu_email"));
			}
			
			else {
				throw new BusinessException("Entered customer email "+email+" doesnt exist");
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return customer;
	}

	@Override
	public Customer searchCustomerById(int id) throws BusinessException {
		Customer customer=new Customer();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select cu_id,cu_fName,cu_lName,cu_email from customer where cu_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer.setId(resultSet.getInt("cu_id"));
				customer.setFirstName(resultSet.getString("cu_fname"));
				customer.setLastName(resultSet.getString("cu_lname"));
				customer.setEmailId(resultSet.getString("cu_email"));
			}
			
			else {
				throw new BusinessException("Entered customer Id "+id+" doesnt exist");
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return customer;
	}

	@Override
	public Customer searchCustomerByOrderId(int orderId) throws BusinessException {
		Customer customer=new Customer();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select cu_id,cu_fName,cu_lName,cu_email from orders join customer on od_cu_id=cu_id where od_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, orderId);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer.setId(resultSet.getInt("cu_id"));
				customer.setFirstName(resultSet.getString("cu_fname"));
				customer.setLastName(resultSet.getString("cu_lname"));
				customer.setEmailId(resultSet.getString("cu_email"));
			}
			
			else {
				throw new BusinessException("Entered order Id :"+orderId+" is invalid");
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return customer;
	}

	@Override
	public List<Customer> searchCustomerByName(String name) throws BusinessException {
		List<Customer> customerList =new ArrayList<>();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select cu_id,cu_fName,cu_lName,cu_email from customer where cu_fname=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Customer customer=new Customer();
				customer.setId(resultSet.getInt("cu_id"));
				customer.setFirstName(resultSet.getString("cu_fname"));
				customer.setLastName(resultSet.getString("cu_lname"));
				customer.setEmailId(resultSet.getString("cu_email"));
				customerList.add(customer);
			}
			
			if(customerList.size()==0) {
				throw new BusinessException("No customer of Name :"+name);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return customerList;
	}

}
