package com.app.model;

public class Order {

	private int id;
	private Customer customer;
	private Product product;
	private String status;
	private double orderCost;
	private int pQuantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product prodcuct) {
		this.product = prodcuct;
	}
	public Order(Customer customer, Product prodcuct) {
		super();
		this.customer = customer;
		this.product = prodcuct;
	}
	
	public Order() {
		super();
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", prodcuct=" + product + ", status=" + status
				+ ", orderCost=" + orderCost + ", pQuantity=" + pQuantity + "]";
	}
	public String print() {
		return id+") "+product.getName()+"-"+pQuantity+"	"+orderCost+"	status:"+status+"\n";
	}
	public double getOrderCost() {
		return orderCost;
	}
	public void setOrderCost(double orderCost) {
		this.orderCost = orderCost;
	}
	public int getpQuantity() {
		return pQuantity;
	}
	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}



	
}
