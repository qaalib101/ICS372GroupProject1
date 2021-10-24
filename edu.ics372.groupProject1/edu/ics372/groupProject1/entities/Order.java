package edu.ics372.groupProject1.entities;

import java.io.Serializable;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private String productId;
	private String productName;
	private int amountOrdered;

	public Order(String productId, String productName, int amountOrdered) {
	}

	public String getProductName() {
		return this.productName;
	}

	public String getProductId() {
		return this.productId;
	}

	public String getAmount() {
		return Integer.toString(this.amountOrdered);
	}
}
