package com.app.dao;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.CartItem;
import com.app.model.Customer;
import com.app.model.Order;

public interface OrderDAO {

	
	public int orderPlaced(List<CartItem> cartList) throws BusinessException;
	public List<Order> viewAllOrder(Customer customer) throws BusinessException;
	public Order orderShipped(Order order)  throws BusinessException;
	public Order orderRecieved(Order order)  throws BusinessException;
	public List<Order> viewAllCustomerOrder() throws BusinessException;

}
