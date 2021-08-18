package com.app.services.impl;

import com.app.dao.ProductDeleteDAO;
import com.app.dao.impl.ProductDeleteDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Product;
import com.app.services.ProductDeleteService;

public class ProductDeleteServiceImpl implements ProductDeleteService{

	private ProductDeleteDAO productDeleteDAO = new ProductDeleteDAOImpl();
	@Override
	public boolean productDelete(Product product) throws BusinessException {
		
		return productDeleteDAO.productDelete(product);
	}

}
