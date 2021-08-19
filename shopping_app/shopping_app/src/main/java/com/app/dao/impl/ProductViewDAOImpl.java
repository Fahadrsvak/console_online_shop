package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.app.dao.ProductViewDAO;
import com.app.dao.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.Product;


public class ProductViewDAOImpl implements ProductViewDAO{

	@Override
	public List<Product> productView() throws BusinessException {
		
		List<Product> productList=new ArrayList<>();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select pr_id,pr_name,pr_price,pr_brandName from product";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Product product=new Product();
				product.setId(resultSet.getInt("pr_id"));
				product.setName(resultSet.getString("pr_name"));
				product.setPrice(resultSet.getDouble("pr_price"));
				product.setBrandName(resultSet.getString("pr_brandName"));
				productList.add(product);
			}
			
			if(productList.size()==0) {
				throw new BusinessException("No Products added try adding products");
			}
		} catch (ClassNotFoundException | SQLException e) {
			//log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return productList;
	}



}
