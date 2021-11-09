package edu.ics372.groupProject1.tests;

import java.util.Iterator;
import java.util.LinkedList;
import edu.ics372.groupProject1.facade.Result;
import edu.ics372.groupProject1.collections.MemberList;
import edu.ics372.groupProject1.collections.OrderList;
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
	private String[] orderAmount = {"22", "24", "26"};
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
		Iterator<Result> iterator = store.listMembers();

		while (iterator.hasNext()) {
			int id = 0;
			Result actual = iterator.next();
			MemberList expected = MemberList.getInstance();
			assert actual.getMemberId().equals("M" + id);
			assert actual.getMemberName().equals(names[id]);
			assert actual.getMemberAddress().equals(addresses[id]);
			++id;

		}
	}
	
	/**
	 * Tests a list of Products
	 */

	public void testListProducts() {
		Iterator<Result> testProducts = store.listProducts();

		while (testProducts.hasNext()) {
			int id = 0;
			Result actual = testProducts.next();
			Inventory expected = Inventory.getInstance();
			assert actual.getProductId().equals("P" + id);
			assert actual.getProductName().equals(storeItems[id]);
			assert actual.getProductCurrentPrice().equals(price[id]);
			assert actual.getProductMinimumReorderLevel().equals(reorderLevel[id]);
			++id;

		}

	}
	
	/**
	 *Test a list of outstanding orders 
	 */

	public void testListOrders() {
		Iterator<Result> testOrders = store.listOrders();

		while (testOrders.hasNext()) {
			int id = 0;
			Result actual = testOrders.next();
			OrderList expected = OrderList.getInstance();
			assert actual.getProductId().equals("P" + id);
			assert actual.getProductName().equals(storeItems[id]);
			assert actual.getAmountOrdered().equals(orderAmount[id]);
			++id;
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