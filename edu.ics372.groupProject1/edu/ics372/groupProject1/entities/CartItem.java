package edu.ics372.groupProject1.entities;

import java.text.DecimalFormat;

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
	DecimalFormat df = new DecimalFormat("0.00");

	public CartItem(Product productName, int numberOfUnits) {
		super();
		this.productName = productName;
		this.numberOfUnits = numberOfUnits;
	}

	public double getTotalItemPrice() {
		return this.getUnitPrice() * numberOfUnits;

	}

	public double getUnitPrice() {
		return Double.parseDouble(productName.getPrice());
	}

	public Product getProduct() {
		return productName;
	}

	public int getNumberOfUnits() {
		return this.numberOfUnits;
	}

	public String printItemDetails() {
		return String.format("%8s %-3d $%-5f $%-5f \n", getProduct(), getNumberOfUnits(), df.format(getUnitPrice()),
				df.format(getTotalItemPrice()));
	}

	// add possible cartItemIsProduct() for checking?

}
