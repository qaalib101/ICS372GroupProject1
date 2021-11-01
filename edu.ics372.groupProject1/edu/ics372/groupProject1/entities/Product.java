package edu.ics372.groupProject1.entities;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private double price;
	private String id;
	private int reorderLevel;
	private int quantity;
	private static final String PRODUCT_STRING = "P";
	private static int idCounter;

	public Product(String name, double price, int reorderLevel) {
		this.name = name;
		this.price = price;
		this.reorderLevel = reorderLevel;
		this.quantity = 0;
		id = PRODUCT_STRING + ++idCounter;
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(price);
	}

	public String getReorderLevel() {
		return Integer.toString(reorderLevel);
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

	public boolean changePrice(String newPrice) {
		try {
			price = Double.parseDouble(newPrice);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
