package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.app.dao.ProductUpdateDAO;
import com.app.dao.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.Product;

public class ProductUpdateDAOImpl implements ProductUpdateDAO{

	@Override
	public Product productUpdate(Product product) throws BusinessException {
		
		try(Connection connection =MySqlDbConnection.getConnection()){
			String sql="update product set pr_name=?,pr_price=?,pr_brandName=? where pr_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(2, product.getPrice());
			preparedStatement.setString(3, product.getBrandName());
			preparedStatement.setInt(4, product.getId());
			
			int c =preparedStatement.executeUpdate();
			if(c!=1) {
			 throw new BusinessException("producted not update due to Product id miss match");
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error occured contact system admin");
		}
		return product;
	}


}
