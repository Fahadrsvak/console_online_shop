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
		
		product= productAddDAO.productAdd(product);
		if(!(product.getId()>0)){
			throw new BusinessException("Failed to add product to database");
		}
		else {
			return product;
		}
	}

}
