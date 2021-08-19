package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.CartItemDAO;
import com.app.dao.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.CartItem;
import com.app.model.Customer;
import com.app.model.Product;


public class CartItemDAOImpl implements CartItemDAO {

	@Override
	public CartItem addItem(CartItem cartItem) throws BusinessException {
		try(Connection connection =MySqlDbConnection.getConnection()){
			String sql="insert into cart_items(ct_pr_id,ct_cu_id,ct_quantity,ct_lineTotal) values(?,?,?,?) ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, cartItem.getProduct().getId());
			preparedStatement.setInt(2, cartItem.getCustomer().getId());
			preparedStatement.setInt(3, cartItem.getQuantity());
			preparedStatement.setDouble(4, cartItem.getLineTotal());

			
			int c =preparedStatement.executeUpdate();
			if(c==1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()) {
					cartItem.setId(resultSet.getInt(1));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error occured contact system admin");
		}
		return cartItem;
	}

	@Override
	public List<CartItem> getAllCartItem(Customer customer) throws BusinessException {
		List<CartItem> cartList=new ArrayList<>();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select ct_id,ct_quantity,ct_lineTotal,pr_id,pr_name,pr_price,pr_brandName from cart_items ct inner join product pr on ct.ct_pr_id=pr.pr_id where ct.ct_cu_id=?";
			
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,customer.getId() );
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				CartItem cartItem=new CartItem();
				cartItem.setId(resultSet.getInt("ct_id"));
				cartItem.setQuantity(resultSet.getInt("ct_quantity"));
				cartItem.setLineTotal(resultSet.getDouble("ct_lineTotal"));
				Product product = new Product();
				product.setId(resultSet.getInt("pr_id"));
				product.setName(resultSet.getString("pr_name"));
				product.setPrice(resultSet.getDouble("pr_price"));
				product.setBrandName(resultSet.getString("pr_brandName"));
				cartItem.setProduct(product);
				cartItem.setCustomer(customer);
				cartList.add(cartItem);
			}
			
			if(cartList.size()==0) {
				throw new BusinessException("No Products added to cart . try adding products");
			}
		} catch (ClassNotFoundException | SQLException e) {
			//log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return cartList;
	}


	
}
