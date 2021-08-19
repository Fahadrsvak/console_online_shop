package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.dao.ProductDeleteDAO;
import com.app.dao.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.Product;

public class ProductDeleteDAOImpl implements ProductDeleteDAO{

	@Override
	public boolean productDelete(Product product) throws BusinessException {
		try(Connection connection =MySqlDbConnection.getConnection()){
			String sql="delete from product  where pr_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, product.getId());
			
			int c =preparedStatement.executeUpdate();
			if(c!=1) {
			 throw new BusinessException("producted not update due to Product id miss match");
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error occured contact system admin");
		}
		return true;
	}

}
