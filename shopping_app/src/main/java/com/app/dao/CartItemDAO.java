package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.CartItem;


public interface CartItemDAO {

	public CartItem addItem(CartItem cartItem) throws BusinessException;
	
}
