package com.app.services;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductAddService {
	public Product productAdd(Product product) throws BusinessException;

}
