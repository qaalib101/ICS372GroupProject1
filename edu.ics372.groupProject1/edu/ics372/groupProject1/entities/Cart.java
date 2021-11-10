package edu.ics372.groupProject1.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *         Cart Class Holds a list of products to be checked out by the
 *         customer.
 *
 * @author Qaalib Farah, Ayden Sinn, Nate Goetsch, Leng Vang, John Quinlan
 * 
 */
public class Cart implements Serializable {
	private double totalPrice;
	private List<CartItem> cartItems;
	private List<Product> reorderList;
	DecimalFormat df = new DecimalFormat("0.00");

	public Cart() {
		super();
		this.totalPrice = 0;
		this.cartItems = new ArrayList<CartItem>();
		this.reorderList = new ArrayList<Product>();
	}

	/**
	 * calculateCartTotal calculates cart total for current cart instance.
	 * 
	 * @param List<CartItem>
	 * @return double
	 * 
	 */
	public double calculateCartTotal(List<CartItem> cartItems) {

		this.totalPrice = cartItems.stream().mapToDouble(i -> i.getTotalItemPrice()).sum();
		return totalPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @author jquin
	 * 
	 *         getTotalPrice. Returns the totalPrice field.
	 * 
	 * @param N/A
	 * @return double
	 * 
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * addCartItem method adds CartItem to the Cart ArrayList
	 *
	 * @param CartItem
	 * @return boolean
	 *
	 */
	public boolean addCartItem(CartItem cartItem) {
		try {
			cartItems.add(cartItem);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Product> getReorderList() {
		return reorderList;
	}

	/**
	 * addToReorderList Method Adds Products that have been checked out that have
	 * broken the reorder threshold.
	 * 
	 * @param Product
	 * @return boolean
	 *
	 */
	public boolean addToReorderList(Product product) {
		try {
			reorderList.add(product);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author jquin
	 * 
	 *         getReorderList. Returns the list of products to be reordered after
	 *         checkout complete.
	 * 
	 * @param N/A
	 * @return List<Product>
	 * 
	 */
	public List<Product> getReorderList() {
		return reorderList;
	}

	/**
	 * addToReorderList Method Adds Products that have been checked out that have
	 * broken the reorder threshold.
	 * 
	 * @param Product
	 * @return boolean
	 *
	 */
	public boolean addToReorderList(Product product) {
		try {
			reorderList.add(product);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * getCartItems Method return a list of all the items in the cart.
	 *
	 */
	public List<CartItem> getCartItems() {
		return this.cartItems;
	}

	/**
	 * printCartTotal prints the total of all cart items per specified requirements.
	 * 
	 * @param N/A
	 * @return void
	 * 
	 */
	public void printCartTotal() {
		String total = "Grand Total";
		String totalString = String.format("%30s                      $%6s", total, df.format(getTotalPrice()));
		System.out.println("___________________________________________________________");
		System.out.println(totalString);
	}

}
