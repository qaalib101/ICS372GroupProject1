package edu.ics372.groupProject1.entities;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * @author jquin
 *
 */
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
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(currentPrice);
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

	public boolean changePrice(String newPrice) {
		try {
			currentPrice = Double.parseDouble(newPrice);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * @author jquin productInfo method Business Process #6. Retrieve product info
	 *         print string of Product name, ID, price per unit and current stock.
	 */
	public String productInfo() {
		return "Product [name:" + name + ", ID: " + id + ", Price: " + currentPrice + ", Stock: " + quantity;

	}
}
