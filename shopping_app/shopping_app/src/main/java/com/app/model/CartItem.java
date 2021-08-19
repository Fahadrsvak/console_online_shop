package com.app.model;

public class CartItem {
	private int id;
	private Customer customer;
	private Product product;
	private int quantity;
	private double lineTotal;
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
		this.lineTotal=(product.getPrice()*quantity);
	}
	public CartItem() {
		// TODO Auto-generated constructor stub
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
	public double getLineTotal() {
		return lineTotal;
	}
	public void setLineTotal(double d) {
		this.lineTotal = d;
	}
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", customer=" + customer + ", product=" + product + ", quantity=" + quantity
				+ ", lineTotal=" + lineTotal + "]";
	}
	
	
}
