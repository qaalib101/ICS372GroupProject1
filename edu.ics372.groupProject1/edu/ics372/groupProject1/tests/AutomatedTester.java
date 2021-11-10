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
import edu.ics372.groupProject1.ui.UserInterface;
import edu.ics372.groupProject1.facade.Request;

/**
 * This class generates automated tests for the library system using asserts.
 * 
 * @author Qaalib Farah, Ayden Sinn, Nate Goetsch, Leng Vang, John Quinlan
 *
 */
public class AutomatedTester {
	private GroceryStore store;
	private UserInterface UI;
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

	public void generatateTestData() {
		// create memebers
		store.addMember("Solaire", "1271 Walton St. Saint Paul, MN,55668", "1234567788", "02/01/1955", 0);
		store.addMember("Gwyndolin", "123 Easy St. NewYork, NY, 11225", "1234568855", "01/22/05", 0);
		store.addMember("Andre", "123 Walketon Ave. Stockton, NJ, 44589", "5564498756", "02/22/1926", 0);
		store.addMember("Yuria", "456 Shelly Ave., CastleVania, KS, 11122", "5556668888", "01/01/2001", 0);
		store.addMember("Eldritch", "000 Irithyll lane, Irithyll, AK, 00000", "0000001111", "09/21/1311", 0);

		// create Products
		store.addProduct("Milk", 3.75, 20);
		store.addProduct("Eggs", 1.99, 20);
		store.addProduct("Wheat Bread", 2.76, 20);
		store.addProduct("Orange Juice", 3.26, 20);
		store.addProduct("Iced Tea", 2.25, 20);
		store.addProduct("Apple", 1.00, 20);
		store.addProduct("Strawberrys", 3.11, 30);
		store.addProduct("Bananas", 1.01, 15);
		store.addProduct("Avacado", 1.33, 15);
		store.addProduct("Chicken Noodle Soup Canned", 1.45, 15);
		store.addProduct("Hawaiian Buns", 6.55, 10);
		store.addProduct("Turkey Breasts", 5.55, 10);
		store.addProduct("Roast Beef", 4.25, 15);
		store.addProduct("Brown Rice", .88, 20);
		store.addProduct("Tomato Sause", 1.13, 20);
		store.addProduct("BBQ Sause", 2.22, 15);
		store.addProduct("Shrimp", 13.99, 16);
		store.addProduct("Butter", 2.45, 25);
		store.addProduct("Hummus", 3.69, 14);
		store.addProduct("Chocolate Bar", 1.75, 30);
		store.addProduct("Spinach", .33, 15);

	}

	private void testCheckOutCart() {
		generatateTestData();
		UI.retrieveProduct();
		UI.checkoutCart();
	}

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

	public void testAll() {
		testCheckOutCart();
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