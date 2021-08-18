package com.app.model;

public class CartItem {
	private int id;
	private Customer customer;
	private Product product;
	private int quantity;
	private int lineTotal;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public CartItem(Customer customer, Product product, int quantity) {
		super();
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getLineTotal() {
		return lineTotal;
	}
	public void setLineTotal(int lineTotal) {
		this.lineTotal = lineTotal;
	}
	
	
}
