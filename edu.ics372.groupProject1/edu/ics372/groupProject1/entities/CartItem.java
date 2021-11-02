package edu.ics372.groupProject1.entities;

/**
 * @author jquin
 *
 *         CartItem Class Holds data for specific Item in the Cart.
 *
 *
 */
public class CartItem {

	private Product productName;
	private int numberOfUnits;
	private double totalItemPrice;
	private double unitPrice;

	public CartItem(Product productName, int numberOfUnits) {
		super();
		this.productName = productName;
		this.numberOfUnits = numberOfUnits;
	}

	public double getTotalItemPrice() {
		return this.getUnitPrice() * numberOfUnits;

	}

	public double getUnitPrice() {
		return Double.parseDouble(productName.getCurrentPrice());
	}

	public Product getProduct() {
		return productName;
	}

	public int getNumberOfUnits() {
		return this.numberOfUnits;
	}

	// add possible cartItemIsProduct() for checking?

}
