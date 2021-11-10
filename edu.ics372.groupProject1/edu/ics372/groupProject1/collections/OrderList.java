package edu.ics372.groupProject1.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.groupProject1.entities.Order;

/**
 * The collection class for unfulfilled Order objects
 * 
 * @author Qaalib Farah, Ayden Sinn, Nate Goetsch, Leng Vang, John Quinlan
 *
 */
public class OrderList implements Iterable<Order>, Serializable {
	private static final long serialVersionUID = 1L;
	private List<Order> orders = new LinkedList<Order>();
	private static OrderList orderList;

	private OrderList() {

	}

	public static OrderList getInstance() {
		if (orderList == null) {
			orderList = new OrderList();
		}
		return orderList;
	}

	/**
	 * Searches for an order with the given product ID
	 * 
	 * @param productId the id of the product ordered
	 * @return order if the object is found, null if not found
	 */
	public Order search(String productId) {
		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order order = iterator.next();
			if (order.getProductId().equals(productId)) {
				return order;
			}
		}
		return null;
	}

	/**
	 * Method to remove an Order object from the system
	 * 
	 * @param productId id of product that was ordered
	 * @return true if order is found and removed
	 */
	public boolean removeOrder(String productId) {
		Order order = search(productId);
		if (order == null) {
			return false;
		} else {
			return orders.remove(order);
		}
	}

	/**
	 * Method to insert into order collection
	 * 
	 * @param order object to be inserted
	 * @return true if order is inserted. Currently always true
	 */
	public boolean insertOrder(Order order) {
		orders.add(order);
		return true;
	}

	/**
	 * Returns an iterator of all orders
	 * 
	 * @return iterator of collection
	 */
	public Iterator<Order> iterator() {
		return orders.iterator();
	}

	@Override
	public String toString() {
		return orders.toString();
	}

}
