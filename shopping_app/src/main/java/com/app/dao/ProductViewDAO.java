package com.app.dao;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductViewDAO {
	
	public List<Product> productView() throws BusinessException;

}
