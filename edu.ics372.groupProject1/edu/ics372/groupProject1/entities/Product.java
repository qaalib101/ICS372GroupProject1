package edu.ics372.groupProject1.entities;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private double currentPrice;
	private String id;
	private int minimumReorderLevel;
	private int quantity;

	public Product(String name, double currentPrice, int minimumReorderLevel) {
		this.name = name;
		this.currentPrice = currentPrice;
		this.minimumReorderLevel = minimumReorderLevel;
		this.quantity = 0;
	}

	public String getName() {
		return name;
	}

	public String getCurrentPrice() {
		return Double.toString(currentPrice);
	}

	public String getMinimumReorderLevel() {
		return Integer.toString(minimumReorderLevel);
	}

	public String getQuantity() {
		return Integer.toString(quantity);
	}

	public String getId() {
		return id;
	}

	public boolean restock(int newQuantity) {
		quantity += newQuantity;
		return true;
	}
}
