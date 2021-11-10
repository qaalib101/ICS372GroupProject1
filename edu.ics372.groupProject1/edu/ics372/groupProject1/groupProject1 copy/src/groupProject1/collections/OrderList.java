package groupProject1.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import groupProject1.entities.Order;

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

	public Order search(String productId) {
		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order order = (Order) iterator.next();
			if (order.getProductId().equals(productId)) {
				return order;
			}
		}
		return null;
	}

	public boolean removeOrder(String productId) {
		Order order = search(productId);
		if (order == null) {
			return false;
		} else {
			return orders.remove(order);
		}
	}

	/**
	 * Inserts a member into the collection
	 * 
	 * @param member the member to be inserted
	 * @return true iff the member could be inserted. Currently always true
	 */
	public boolean insertOrder(Order order) {
		orders.add(order);
		return true;
	}

	public Iterator<Order> iterator() {
		return orders.iterator();
	}

	/**
	 * String form of the collection
	 * 
	 */
	@Override
	public String toString() {
		return orders.toString();
	}

}
