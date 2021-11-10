package edu.ics372.groupProject1.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Order represents an order placed for a product
 * 
 * @author Qaalib Farah, Ayden Sinn, Nate Goetsch, Leng Vang, John Quinlan
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

	@Override
	public int hashCode() {
		return Objects.hash(amountOrdered, productId, productName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return amountOrdered == other.amountOrdered && Objects.equals(productId, other.productId)
				&& Objects.equals(productName, other.productName);
	}

	@Override
	public String toString() {
		return "Product Name: " + this.productName + " Product Id: " + this.productId + " Amount Ordered: "
				+ this.amountOrdered + "\n";
	}

}
