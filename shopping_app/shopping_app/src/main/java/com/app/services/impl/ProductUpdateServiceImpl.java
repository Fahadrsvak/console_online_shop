package com.app.services.impl;

import com.app.dao.ProductUpdateDAO;
import com.app.dao.impl.ProductUpdateDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Product;
import com.app.services.ProductUpdateService;

public class ProductUpdateServiceImpl implements ProductUpdateService {
	private ProductUpdateDAO productUpdateDAO = new ProductUpdateDAOImpl();
	@Override
	public Product productUpdate(Product product) throws BusinessException {
		
		return productUpdateDAO.productUpdate(product);
	}

}
