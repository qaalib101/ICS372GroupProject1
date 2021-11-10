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

	/**
	 * @author jquin
	 * 
	 *         CartItem Class constructor.
	 * 
	 * @param Product, int
	 * @return N/A
	 * 
	 */
	public CartItem(Product product, int numberOfUnits) {
		super();
		this.product = product;
		this.numberOfUnits = numberOfUnits;
	}

	/**
	 * @author jquin
	 * 
	 *         getTotalItemPrice. Calculates the price total of the cart item.
	 * 
	 * @param N/A
	 * @return double
	 * 
	 */
	public double getTotalItemPrice() {
		return this.getUnitPrice() * numberOfUnits;

	}

	/**
	 * @author jquin
	 * 
	 *         getUnitPrice. returns the unit price field.
	 * 
	 * @param N/A
	 * @return double
	 * 
	 */
	public double getUnitPrice() {
		return Double.parseDouble(product.getPrice());
	}

	/**
	 * @author jquin
	 * 
	 *         getProduct. returns the product field.
	 * 
	 * @param N/A
	 * @return Product
	 * 
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @author jquin
	 * 
	 *         getNumberOfUnits. returns the number of units of the particular item
	 *         in the CartItem instance.
	 * 
	 * @param N/A
	 * @return int
	 * 
	 */
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
