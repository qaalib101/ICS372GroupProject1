package edu.ics372.groupProject1.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import edu.ics372.groupProject1.collections.Inventory;

/**
 * @author jquin
 *
 *         Cart Class Holds a list of products to be checked out by the customer
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
	 * calculateCartTotal Method Calculates and returns the running cart total as
	 * double.
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
	 * addCartItem Method Adds CartItem to the Cart ArrayList returns True if
	 * successful, False if failed or exception
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
	 * cartItemProductIDValid Method Checks whether the Product to be entered is
	 * valid
	 */
	public boolean cartItemProductIDValid(CartItem cartItem) {
		boolean isValid = Inventory.getInstance().getInventoryList().stream()
				.anyMatch(cartItem1 -> cartItem1.getId().equals(cartItem.getProduct().getId()));
		return isValid;
	}

	/**
	 * getCartItems Method return a list of all the items in the cart.
	 *
	 */
	public List<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void printCartTotal() {
		String total = "Grand Total";
		String totalString = String.format("%30s                      $%6s", total, df.format(getTotalPrice()));
		System.out.println("___________________________________________________________");
		System.out.println(totalString);
	}

}
