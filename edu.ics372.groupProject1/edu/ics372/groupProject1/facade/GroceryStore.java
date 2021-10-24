package edu.ics372.groupProject1.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.ics372.groupProject1.collections.Inventory;
import edu.ics372.groupProject1.collections.MemberList;
import edu.ics372.groupProject1.collections.OrderList;
import edu.ics372.groupProject1.entities.Order;
import edu.ics372.groupProject1.entities.Product;

public class GroceryStore {
	private static final long serialVersionUID = 1L;
	private Inventory inventory = Inventory.getInstance();
	private MemberList members = MemberList.getInstance();
	private OrderList orders = OrderList.getInstance();
	private static GroceryStore store;

	/**
	 * Private for the singleton pattern Creates the catalog and member collection
	 * objects
	 */
	private GroceryStore() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static GroceryStore instance() {
		if (store == null) {
			return store = new GroceryStore();
		} else {
			return store;
		}
	}

	public Result processShipment(Request request) {
		Result result = new Result();
		String productId = request.getProductId();
		Product product = inventory.search(productId);
		if (product == null) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			return result;
		}
		Order order = orders.search(productId);
		if (order == null) {
			result.setResultCode(Result.NO_OUTSTANDING_ORDER);
			return result;
		}
		if (product.restock(Integer.parseInt(order.getAmount())) && orders.removeOrder(productId)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setProductFields(product);
		} else {
			result.setResultCode(Result.OPERATION_FAILED);
		}
		return result;
	}

	/**
	 * Retrieves a deserialized version of the library from disk
	 * 
	 * @return a GroceryStore object
	 */
	public static GroceryStore retrieve() {
		try {
			FileInputStream file = new FileInputStream("GroceryStoreData");
			ObjectInputStream input = new ObjectInputStream(file);
			store = (GroceryStore) input.readObject();
//			Member.retrieve(input);
			return store;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

	/**
	 * Serializes the Library object
	 * 
	 * @return true iff the data could be saved
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("CooperativeData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(store);
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	/**
	 * String form of the library
	 * 
	 */
	@Override
	public String toString() {
		return inventory + "\n" + members;
	}
}
