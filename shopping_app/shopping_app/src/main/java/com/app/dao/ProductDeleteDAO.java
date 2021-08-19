package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductDeleteDAO {
	
	public boolean productDelete(Product product) throws BusinessException;

}
