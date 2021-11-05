package edu.ics372.groupProject1.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.groupProject1.entities.Product;

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

	private void add(Product product) {
		// TODO Auto-generated method stub

	}

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
	 * Returns an iterator to all books
	 * 
	 * @return iterator to the collection
	 */
	public Iterator<Product> iterator() {
		return products.iterator();

	}

	public List<Product> getInventoryList() {
		return products;
	}

	/**
	 * String form of the collection
	 * 
	 */
	public String toString() {
		return products.toString();
	}

}
