package com.app.services.impl;

import com.app.dao.ProductAddDAO;
import com.app.dao.impl.ProductAddDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Product;
import com.app.services.ProductAddService;

public class ProductAddServiceImpli implements ProductAddService {
	private ProductAddDAO productAddDAO =new ProductAddDAOImpl();
	@Override
	public Product productAdd(Product product) throws BusinessException {
		
		return productAddDAO.productAdd(product);
	}

}
