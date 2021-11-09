package edu.ics372.groupProject1.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.groupProject1.entities.Product;

/**
 * The collection class for products
 * 
 * @author
 *
 */
public class Inventory implements Iterable<Product>, Serializable {
	private static final long serialVersionUID = 1L;
	private static Inventory inventory;
	private List<Product> products = new LinkedList<Product>();

	private Inventory() {

	}

	public static Inventory getInstance() {
		if (inventory == null) {
			inventory = new Inventory();
		}
		return inventory;
	}

	/**
	 * Inserts a product into inventory
	 * 
	 * @param product the product to be inserted
	 * @return true if the product could be inserted. Currently always true
	 */
	public boolean insertProduct(Product product) {
		products.add(product);
		return true;
	}

	/**
	 * Method to search collection for product using it id
	 * 
	 * @param productID id of product to be searched for
	 * @return product object found. Null if not found.
	 */
	public Product search(String productID) {
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			if (product.getId().equals(productID)) {
				return product;
			}
		}
		return null;
	}

	public Product searchByName(String productName) {
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			if (product.getName().equals(productName)) {
				return product;
			}
		}
		return null;
	}

	/**
	 * Returns an iterator to all products
	 * 
	 * @return iterator to the collection
	 */
	public Iterator<Product> iterator() {
		return products.iterator();

	}

	/**
	 * String form of the collection
	 * 
	 */
	public String toString() {
		return products.toString();
	}

}
