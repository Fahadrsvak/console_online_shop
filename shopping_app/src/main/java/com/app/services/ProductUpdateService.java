package com.app.services;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductUpdateService {
	
	public Product productUpdate(Product product) throws BusinessException; 


}
