package com.app.services;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.CartItem;
import com.app.model.Customer;


public interface CartItemService {
	
	public CartItem addItem(CartItem cartItem) throws BusinessException;
	public List<CartItem> getAllCartItem(Customer customer) throws BusinessException;
}
