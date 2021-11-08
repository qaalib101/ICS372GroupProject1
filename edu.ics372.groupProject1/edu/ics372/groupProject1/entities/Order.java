package edu.ics372.groupProject1.entities;

import java.io.Serializable;

/**
 * Order represents an order placed for a product
 * 
 * @author Qaalib Farah
 *
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private String productId;
	private String productName;
	private int amountOrdered;

	public Order(String productId, String productName, int amountOrdered) {
		this.productId = productId;
		this.productName = productName;
		this.amountOrdered = amountOrdered;
	}

	/**
	 * Method to get product name.
	 * 
	 * @return name of product ordered
	 */
	public String getProductName() {
		return this.productName;
	}

	/**
	 * Method to get product id.
	 * 
	 * @return id of product ordered.
	 */
	public String getProductId() {
		return this.productId;
	}

	/**
	 * Method to get amount of product ordered.
	 * 
	 * @return amount ordered
	 */
	public String getAmountOrdered() {
		return Integer.toString(this.amountOrdered);
	}

}
