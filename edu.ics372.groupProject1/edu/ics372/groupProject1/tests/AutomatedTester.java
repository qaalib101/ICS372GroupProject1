package edu.ics372.groupProject1.tests;

import java.util.Iterator;
import java.util.LinkedList;
import edu.ics372.groupProject1.facade.Result;
import edu.ics372.groupProject1.collections.MemberList;
import edu.ics372.groupProject1.collections.Inventory;
import edu.ics372.groupProject1.entities.Cart;
import edu.ics372.groupProject1.entities.Member;
import edu.ics372.groupProject1.entities.Order;
import edu.ics372.groupProject1.entities.Product;
import edu.ics372.groupProject1.facade.GroceryStore;
import edu.ics372.groupProject1.facade.Request;


/**
 * This class generates automated tests for the library system using
 * asserts.
 * 
 * @author 
 *
 */
public class AutomatedTester {
	private GroceryStore store;
	private String[] names = { "n1", "n2", "n3" };
	private String[] addresses = { "a1", "a2", "a3" };
	private String[] phones = { "p1", "p2", "p3" };
	private Member[] members = new Member[3];
	private String[] storeItems = { "Apple", "Steak", "Eggs" };
	private String[] price = { "1.50", "3.25", "6.00" };
	private String[] reorderLevel = { "11", "12", "13" };
	private Product[] products = new Product[3];
	private String[] ids = { "i1", "i2", "i3", "i4", "i5", "i6" };
	private Cart[] cart = new Cart[6];

	/**
	 * Tests Member creation.
	 */
	public void testAddMember() {
		for (int count = 0; count < members.length; count++) {
			Request.instance().setMemberAddress(addresses[count]);
			Request.instance().setMemberName(names[count]);
			Result result = store.instance().addMember(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getMemberName().equals(names[count]);
			assert result.getMemberPhone().equals(phones[count]);
		}
	}

	public void testAddProduct() {
		for (int count = 0; count < products.length; count++) {
			Request.instance().setProductCurrentPrice(price[count]);
			Request.instance().setProductName(storeItems[count]);
			Request.instance().setProductMinimumReorderLevel(ids[count]);
			Result result = store.instance().addProduct(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(storeItems[count]);
			assert result.getProductCurrentPrice().equals(price[count]);
			assert result.getProductMinimumReorderLevel().equals(reorderLevel[count]);
		}
	}

//	public void testAddItemToCart() {
//		for(int count = 0; count < cart.length; count++) {
//			Request.instance()
//		}
//	}

	public void testSearchMembership() {
		Request.instance().setMemberId("M1");
		// assert
		// Library.instance().searchMembership(Request.instance()).getMemberId().equals("M1");
		Request.instance().setMemberId("M4");
		// assert Library.instance().searchMembership(Request.instance()) == null;
	}
	
	/**
	 * Tests a list of Members
	 */

	public void testListMembers() {
		Iterator<Result> testMembers = store.listMembers();

		while (testMembers.hasNext()) {
			int i = 0;
			Result actual = testMembers.next();
			MemberList expected = MemberList.getInstance();
			assert actual.getMemberId().equals(i);
			assert actual.getMemberName().equals(names[i]);
			assert actual.getMemberAddress().equals(addresses[i]);
			i++;

		}
	}
	
	/**
	 * Tests a list of Products
	 */

	public void testListProducts() {
		Iterator<Result> testProducts = store.listProducts();

		while (testProducts.hasNext()) {
			int i = 0;
			Result actual = testProducts.next();
			Inventory expected = Inventory.getInstance();
			assert actual.getProductId().equals(i);
			assert actual.getProductName().equals(names[i]);
			assert actual.getProductCurrentPrice().equals(price[i]);
			assert actual.getProductMinimumReorderLevel().equals(reorderLevel[i]);
			i++;

		}

	}
	
	/**
	 *Test a list of outstanding orders 
	 */

	public void testListOrders() {
		Iterator<Result> testOrders = store.listOrders();
		Order order1 = new Order("P1", "Apple", 22);
		Order order2 = new Order("P2", "Steak", 24);
		Order order3 = new Order("P3", "Eggs", 26);
		LinkedList<Order> orderList = new LinkedList<Order>();
		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);

		while (testOrders.hasNext()) {
			int i = 0;
			Result actual = testOrders.next();
			Order expected = orderList.get(i);
			assert expected.getProductId().equals(actual.getOrderProductId());
			assert expected.getProductName().equals(actual.getOrderProductName());
			assert expected.getAmountOrdered().equals(actual.getAmountOrdered());
			i++;
		}
	}
   

	public void testAll() {
		testAddMember();
		testAddProduct();
		testSearchMembership();
		testListMembers();
		testListProducts();
		testListOrders();
	}

	public static void main(String[] args) {
		new AutomatedTester().testAll();
	}
}