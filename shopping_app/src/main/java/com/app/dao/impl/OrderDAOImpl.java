package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.OrderDAO;
import com.app.dao.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.CartItem;
import com.app.model.Customer;
import com.app.model.Order;
import com.app.model.Product;

public class OrderDAOImpl implements OrderDAO {

	@Override
	public int orderPlaced(List<CartItem> cartList) throws BusinessException {
		int count=0;
		int cu_Id=0;
		try(Connection connection=MySqlDbConnection.getConnection()){
			for (CartItem cartItem : cartList) {
				String sql="insert into orders(od_cu_id,od_pr_id,od_prQuantity) values(?,?,?) ";
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, cartItem.getCustomer().getId());
				preparedStatement.setInt(2, cartItem.getProduct().getId());
				preparedStatement.setInt(3, cartItem.getQuantity());
				cu_Id=cartItem.getCustomer().getId();
				int c =preparedStatement.executeUpdate();
				if(c==1) {
					
						count++;
					}
				}
			String sql="delete from cart_items  where ct_cu_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,cu_Id);
			int c =preparedStatement.executeUpdate();
			if(c==1) {
				
	//			System.out.println("cart cleared");
			}
			
			}
		catch (ClassNotFoundException | SQLException e) {
			//log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return count;
	}

	@Override
	public List<Order> viewAllOrder(Customer customer) throws BusinessException {
		List<Order> orderList=new ArrayList<>();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select od_id,od_prQuantity,od_status,pr_name,pr_price,pr_brandName from orders join product on od_pr_id=pr_id and od_cu_id=? order by od_status";
			
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,customer.getId() );
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Order order=new Order();
//				System.out.println(resultSet.getInt("ct_id"));
//				System.out.println(resultSet.getDouble("ct_lineTotal"));
				order.setId(resultSet.getInt("od_id"));
				order.setpQuantity(resultSet.getInt("od_prQuantity"));
				order.setStatus(resultSet.getString("od_status"));
				Product product = new Product();
				product.setName(resultSet.getString("pr_name"));
				product.setPrice(resultSet.getDouble("pr_price"));
				product.setBrandName(resultSet.getString("pr_brandName"));
				order.setOrderCost(order.getpQuantity()*product.getPrice());
				order.setProduct(product);
				order.setCustomer(customer);
				orderList.add(order);
			}
			
			if(orderList.size()==0) {
				throw new BusinessException("No Products added to cart . try adding products");
			}
		} catch (ClassNotFoundException | SQLException e) {
			//log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return orderList;
	}

	@Override
	public Order orderShipped(Order order) throws BusinessException {
		try(Connection connection =MySqlDbConnection.getConnection()){
			String sql="update orders set od_status=? where od_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, "Shipped");
			preparedStatement.setDouble(2, order.getId());
			
			
			int c =preparedStatement.executeUpdate();
			if(c!=1) {
			 throw new BusinessException("order not update due to order id miss match");
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error occured contact system admin");
		}
		return order;
	}

	@Override
	public Order orderRecieved(Order order) throws BusinessException {
		try(Connection connection =MySqlDbConnection.getConnection()){
			String sql="update orders set od_status=? where od_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, "Delivered");
			preparedStatement.setDouble(2, order.getId());
			
			
			int c =preparedStatement.executeUpdate();
			if(c!=1) {
			 throw new BusinessException("order not update due to order id miss match");
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error occured contact system admin");
		}
		return order;
	}

	@Override
	public List<Order> viewAllCustomerOrder() throws BusinessException {
		List<Order> orderList=new ArrayList<>();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select od_id,od_cu_id,od_prQuantity,od_status,pr_name,pr_price,pr_brandName from orders join product on od_pr_id=pr_id order by od_status";
			
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Order order=new Order();
//				System.out.println(resultSet.getInt("ct_id"));
//				System.out.println(resultSet.getDouble("ct_lineTotal"));
				order.setId(resultSet.getInt("od_id"));
				order.setpQuantity(resultSet.getInt("od_prQuantity"));
				order.setStatus(resultSet.getString("od_status"));
				Product product = new Product();
				product.setName(resultSet.getString("pr_name"));
				product.setPrice(resultSet.getDouble("pr_price"));
				product.setBrandName(resultSet.getString("pr_brandName"));
				Customer customer=new Customer();
				customer.setId(resultSet.getInt("od_cu_id"));
				order.setOrderCost(order.getpQuantity()*product.getPrice());
				order.setProduct(product);
				order.setCustomer(customer);
				order.setCustomer(customer);
				orderList.add(order);
			}
			
			if(orderList.size()==0) {
				throw new BusinessException("No Products added to cart . try adding products");
			}
		} catch (ClassNotFoundException | SQLException e) {
			//log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return orderList;
	}
}
