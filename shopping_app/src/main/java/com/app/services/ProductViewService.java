package com.app.services;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductViewService {

	public List<Product> productView() throws BusinessException;
	public Product selectProduct(List<Product> productList,int productTempId) throws BusinessException;
}
