package com.app.services.impl;

import java.util.List;

import com.app.dao.ProductViewDAO;
import com.app.dao.impl.ProductViewDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Product;
import com.app.services.ProductViewService;

public class ProductViewServiceImpl implements ProductViewService {

	private ProductViewDAO productViewDAO =new ProductViewDAOImpl();
	List<Product> productList=null;
	@Override
	public List<Product> productView() throws BusinessException {
		// TODO Auto-generated method stub
		productList=productViewDAO.productView();
		
			return productList;
		
	}
	
	@Override
	public Product selectProduct(List<Product> productList, int productTempId) throws BusinessException {
		Product productTemp= null;
		for (Product product : productList) {
			if(product.getId()==productTempId) {
			
				productTemp=product;
				
			}
			//System.out.println(product.getId()+"="+productTempId);
		}

		if(productTemp==null){
			throw new BusinessException(" Invalid product ID\n Enter a valid product id");
		}
		return productTemp;
	}

}
