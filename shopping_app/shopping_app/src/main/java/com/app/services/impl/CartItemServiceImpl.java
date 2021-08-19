package com.app.services.impl;


import java.util.ArrayList;
import java.util.List;

import com.app.dao.CartItemDAO;
import com.app.dao.impl.CartItemDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.CartItem;
import com.app.model.Customer;
import com.app.services.CartItemService;

public class CartItemServiceImpl implements CartItemService{
	private CartItemDAO cartItemDAO = new CartItemDAOImpl();

	@Override
	public CartItem addItem(CartItem cartItem) throws BusinessException {
		
		cartItem= cartItemDAO.addItem(cartItem);
		if(!(cartItem.getId()>0)) {
			throw new BusinessException("Failed to add product to cart");
		}
		else {
		
		return cartItem;
		}
	}

	@Override
	public List<CartItem> getAllCartItem(Customer customer) throws BusinessException {
		List<CartItem> cartList = new ArrayList<>();
		cartList=cartItemDAO.getAllCartItem(customer);
		
			return cartList;
		
	
		
	}

	
	
}
