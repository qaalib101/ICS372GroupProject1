package groupProject1.entities;

import java.text.DecimalFormat;

/**
 * @author jquin
 * 
 *         CartItem class holds data regarding the current checkout instance.
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

	/**
	 * printItemDetails prints CartItem details in specified format.
	 * 
	 * @param N/A
	 * @return String
	 * 
	 */
	public String printItemDetails() {
		return String.format("%30s %8d     $%6s $%6s ", getProduct().getName(), getNumberOfUnits(),
				df.format(getUnitPrice()), df.format(getTotalItemPrice()));
	}

}
