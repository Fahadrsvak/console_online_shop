package com.app.services;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductDeleteService {

	public boolean productDelete(Product product) throws BusinessException;
	
}
