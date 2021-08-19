package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductAddDAO {
	
	public Product productAdd(Product product) throws BusinessException;
}
  