package edu.ics372.groupProject1.entities;

import java.text.DecimalFormat;

/**
 * 
 * CartItem class holds data regarding the current checkout instance.
 * 
 * @author Qaalib Farah, Ayden Sinn, Nate Goetsch, Leng Vang, John Quinlan
 *
 */
public class CartItem {

	private Product product;
	private int numberOfUnits;
	private double totalItemPrice;
	private double unitPrice;
	DecimalFormat df = new DecimalFormat("0.00");

	public CartItem(Product product, int numberOfUnits) {
		super();
		this.product = product;
		this.numberOfUnits = numberOfUnits;
	}

	public double getTotalItemPrice() {
		return this.getUnitPrice() * numberOfUnits;

	}

	public double getUnitPrice() {
		return Double.parseDouble(product.getPrice());
	}

	public Product getProduct() {
		return product;
	}

	public int getNumberOfUnits() {
		return this.numberOfUnits;
	}

	/**
	 * toString prints CartItem details in specified format.
	 * 
	 * @param N/A
	 * @return String
	 * 
	 */
	public String toString() {
		return String.format("%30s %8d     $%6s $%6s ", getProduct().getName(), getNumberOfUnits(),
				df.format(getUnitPrice()), df.format(getTotalItemPrice()));
	}
}
