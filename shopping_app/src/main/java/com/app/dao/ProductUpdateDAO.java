package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductUpdateDAO {

	public Product productUpdateName(Product product) throws BusinessException; 
	
	public Product productUpdatePrice(Product product) throws BusinessException;
	
	public Product productUpdateBrandName(Product product) throws BusinessException;
}
