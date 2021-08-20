package com.app.services;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.CartItem;
import com.app.model.Customer;
import com.app.model.Order;


public interface OrderService {
	
	public boolean orderPlaced(List<CartItem> cartList) throws BusinessException;
	public List<Order> viewAllOrder(Customer customer) throws BusinessException;
	public Order orderShipped(Order order)  throws BusinessException;
	public Order orderRecieved(Order order)  throws BusinessException;
	public Order selectOrderCustomer(List<Order> orderList,int orderTempId) throws BusinessException;
	public Order selectOrderEmployee(List<Order> orderList,int orderTempId) throws BusinessException;
	public List<Order> viewAllCustomerOrder() throws BusinessException;
}
