package com.app.services;

import com.app.exception.BusinessException;
import com.app.model.CartItem;


public interface CartItemService {
	
	public CartItem addItem(CartItem cartItem) throws BusinessException;
}
