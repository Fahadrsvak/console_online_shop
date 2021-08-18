package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductUpdateDAO {

	public Product productUpdate(Product product) throws BusinessException; 
	
	
}
