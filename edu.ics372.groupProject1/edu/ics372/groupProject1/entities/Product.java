package edu.ics372.groupProject1.entities;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Class to represent product sold in the grocery store
 * 
 * @author Qaalib Farah, Ayden Sinn, Nate Goetsch, Leng Vang, John Quinlan
 *
 */
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

	/**
	 * Retrieve product name
	 * 
	 * @return name of product
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieve product price
	 * 
	 * @return price of product
	 */
	public String getPrice() {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(price);
	}

	/**
	 * Retrieve product reorder level
	 * 
	 * @return reorderLevel
	 */
	public String getReorderLevel() {
		return Integer.toString(reorderLevel);
	}

	/**
	 * Retrieve product quantity
	 * 
	 * @return quantity
	 */
	public String getQuantity() {
		return Integer.toString(quantity);
	}

	/**
	 * Retrieve product ID
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Method to update the quantity of the product with more units
	 * 
	 * @param newQuantity quantity to be added to product
	 * @return true if quantity was successfully update
	 */
	public boolean restock(int newQuantity) {
		quantity += newQuantity;
		return true;
	}

	/**
	 * Checks whether a product's quantity is below its reorder level
	 * 
	 * @return true if the product should be reordered
	 */
	public boolean belowReorderLvL() {
		if (this.quantity <= this.reorderLevel) {
			return true;
		}
		return false;

	}

	/**
	 * Decrements a product's quantity by the number of units sold
	 * 
	 * @param unitsSold
	 */
	public void decrementQuantity(int unitsSold) {
		this.quantity -= unitsSold;
	}

	/**
	 * Changes a product's price
	 * 
	 * @param newPrice the product's new price
	 * @return true if the price has been changed
	 */
	public boolean changePrice(String newPrice) {
		try {
			price = Double.parseDouble(newPrice);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * creates and returns a string with a product's info
	 * 
	 * @return the product info
	 */
	public String productInfo() {
		return "Product [name:" + name + ", ID: " + id + ", Price: " + price + ", Stock: " + quantity;

	}
}
