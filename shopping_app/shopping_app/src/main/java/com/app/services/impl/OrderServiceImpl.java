package com.app.services.impl;

import java.util.List;

import com.app.dao.OrderDAO;
import com.app.dao.impl.OrderDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.CartItem;
import com.app.model.Customer;
import com.app.model.Order;
import com.app.services.OrderService;

public class OrderServiceImpl implements OrderService {

	OrderDAO orderDAO=new OrderDAOImpl();
	@Override
	public boolean orderPlaced(List<CartItem> cartList) throws BusinessException {

		 int ordersplaced=orderDAO.orderPlaced(cartList);
		 if(ordersplaced==cartList.size()) {
			 
			 return true;
		 }
		 else if(ordersplaced>0) {
			 throw new BusinessException("some of the products are not placed");
		 }
		 else {
			 throw new BusinessException("Failed to place order ");
		 }
	}
	@Override
	public List<Order> viewAllOrder(Customer customer) throws BusinessException {
		
		return orderDAO.viewAllOrder(customer); 
	}
	@Override
	public Order orderShipped(Order order) throws BusinessException {
		
		return orderDAO.orderShipped(order);
	}
	@Override
	public Order orderRecieved(Order order) throws BusinessException {
		
		return orderDAO.orderRecieved(order);
	}
	@Override
	public Order selectOrderCustomer(List<Order> orderList, int orderTempId) throws BusinessException {
		Order orderTemp=null;
		for (Order order : orderList) {
			if(order.getStatus().equals("Shipped")) {
				if(order.getId()==orderTempId) {
					orderTemp=order;
				}
			}
			
		}
		if(orderTemp==null){
			throw new BusinessException(" Invalid order ID\n Enter a valid order id");
		}
		return orderTemp;
	}
	@Override
	public Order selectOrderEmployee(List<Order> orderList, int orderTempId) throws BusinessException {
		Order orderTemp=null;
		for (Order order : orderList) {
			if(order.getStatus().equals("Ordered")) {
				if(order.getId()==orderTempId) {
					orderTemp=order;
				}
			}
			
		}
		if(orderTemp==null){
			throw new BusinessException(" Invalid order ID\n Enter a valid order id");
		}
		return orderTemp;
	}
	@Override
	public List<Order> viewAllCustomerOrder() throws BusinessException {
		
		return orderDAO.viewAllCustomerOrder();
	}
 
}
