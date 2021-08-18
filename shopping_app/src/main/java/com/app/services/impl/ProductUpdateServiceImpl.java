package com.app.services.impl;

import com.app.dao.ProductUpdateDAO;
import com.app.dao.impl.ProductUpdateDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Product;
import com.app.services.ProductUpdateService;

public class ProductUpdateServiceImpl implements ProductUpdateService {
	private ProductUpdateDAO productUpdateDAO = new ProductUpdateDAOImpl();
	@Override
	public Product productUpdateName(Product product) throws BusinessException {
		
		return productUpdateDAO.productUpdateName(product);
	}
	@Override
	public Product productUpdatePrice(Product product) throws BusinessException {
		
		return productUpdateDAO.productUpdatePrice(product);
	}
	@Override
	public Product productUpdateBrandName(Product product) throws BusinessException {
		
		return productUpdateDAO.productUpdateBrandName(product);
	}

}
