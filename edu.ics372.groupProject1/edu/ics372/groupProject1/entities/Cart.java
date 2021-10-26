package edu.ics372.groupProject1.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import com.sun.jdi.InvalidTypeException;

import InitialDelivACode.Node;
import edu.ics372.groupProject1.collections.Inventory;

/**
 * @author jquin
 *
 *         Cart Class Holds a list of products to be checked out by the customer
 *
 */
public class Cart implements Serializable {
	private double totalPrice;
	private ArrayList<CartItem> cartItems;

	public Cart() {
		super();
		this.totalPrice = 0;
		this.cartItems = new ArrayList<CartItem>();
	}

	/**
	 * calculateCartTotal Method
	 *Calculates and returns the running cart total as double.
	 *
	 */
	
	//https://www.baeldung.com/java-stream-sum PLEASE USE FOR SUMMING INVENTORY
	public double calculateCartTotal(ArrayList cartItems) {
		for(int index=0; index < cartItems.size(); index++) {
			cartItems.get(index).
		}
		return totalPrice;
		
	}

	/**
	 * addCartItem Method Adds CartItem to the Cart ArrayList returns True if
	 * successful, False if failed or exception
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
	 * cartItemProductIDValid Method Checks whether the Product to be entered is
	 * valid
	 *
	 */
	public boolean cartItemProductIDValid(CartItem cartItem) {
		// cycle through list of all products and check each ID if
		// it exists in the list
		Iterator inventoryItr = Inventory.getInstance().iterator();

		while (inventoryItr.hasNext()) {
			String currentID = inventoryItr.next().getProductID();
			int result = ProductIDComparator.compare(cartItem.getProduct().getId(), currentID);
			if (result == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * getCartItems Method return a list of all the items in the cart.
	 *
	 */
	public ArrayList<CartItem> getCartItems() {
		return this.cartItems;
	}

	/**
	 * productIDComparator.compare productID field comparator
	 *
	 */
	class ProductIDComparator implements Comparator<Product> {

		public int compare(Product product1, Product product2) {
			return product1.getId().compareTo(product2.getId());
		}

	}

}
