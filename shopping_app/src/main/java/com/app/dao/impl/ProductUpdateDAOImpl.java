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
	public Product productUpdateName(Product product) throws BusinessException {
		
		try(Connection connection =MySqlDbConnection.getConnection()){
			String sql="update product set pr_name=? where pr_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setInt(2, product.getId());
			
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

	@Override
	public Product productUpdatePrice(Product product) throws BusinessException {
		try(Connection connection =MySqlDbConnection.getConnection()){
			String sql="update product set pr_price=? where pr_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setDouble(1, product.getPrice());
			preparedStatement.setInt(2, product.getId());
			
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

	@Override
	public Product productUpdateBrandName(Product product) throws BusinessException {
		try(Connection connection =MySqlDbConnection.getConnection()){
			String sql="update product set pr_brandName=? where pr_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getBrandName());
			preparedStatement.setInt(2, product.getId());
			
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
