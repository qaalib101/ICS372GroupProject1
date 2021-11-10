package edu.ics372.groupProject1.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Cart Class Holds a list of products to be checked out by the customer.
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
	 * calculateCartTotal calculates cart total for current cart instance
	 * 
	 * @param List<CartItem>
	 * @return the total price of all items in the cart
	 */
	public double calculateCartTotal(List<CartItem> cartItems) {

		this.totalPrice = cartItems.stream().mapToDouble(i -> i.getTotalItemPrice()).sum();
		return totalPrice;
	}

	/**
	 * Returns the totalPrice field
	 * 
	 * @return totalPrice
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * adds CartItem to the Cart ArrayList
	 *
	 * @param CartItem the item to be added to the cart
	 * @return true if the item was added to the cart
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

	/**
	 * Returns the list of products to be reordered after the checkout is complete.
	 * 
	 * @return reorderList
	 */
	public List<Product> getReorderList() {
		return reorderList;
	}

	/**
	 * Adds a product to reorderList when its amount is less than its minimum
	 * reorder level.
	 * 
	 * @param product the product being added to reorderList
	 * @return true if the product has been added to the list
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
	 * @return cartItems
	 *
	 */
	public List<CartItem> getCartItems() {
		return this.cartItems;
	}

	/**
	 * Prints the total of all cart items per specified requirements.
	 */
	public void printCartTotal() {
		String total = "Grand Total";
		String totalString = String.format("%30s                      $%6s", total, df.format(getTotalPrice()));
		System.out.println("___________________________________________________________");
		System.out.println(totalString);
	}

}
