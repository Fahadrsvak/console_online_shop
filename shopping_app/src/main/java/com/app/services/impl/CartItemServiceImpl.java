package com.app.services.impl;


import com.app.dao.CartItemDAO;
import com.app.dao.impl.CartItemDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.CartItem;
import com.app.services.CartItemService;

public class CartItemServiceImpl implements CartItemService{
	private CartItemDAO cartItemDAO = new CartItemDAOImpl();

	@Override
	public CartItem addItem(CartItem cartItem) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
