package com.app.services;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductUpdateService {
	
	public Product productUpdateName(Product product) throws BusinessException; 

	public Product productUpdatePrice(Product product) throws BusinessException;
	
	public Product productUpdateBrandName(Product product) throws BusinessException;
}
