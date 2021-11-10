package edu.ics372.groupProject1.tests;

import edu.ics372.groupProject1.entities.Cart;
import edu.ics372.groupProject1.entities.Member;
import edu.ics372.groupProject1.entities.Product;
import edu.ics372.groupProject1.facade.GroceryStore;
import edu.ics372.groupProject1.facade.Request;
import edu.ics372.groupProject1.facade.Result;
import edu.ics372.groupProject1.ui.UserInterface;

/**
 * This class generates automated tests for the library system using asserts.
 * 
 * @author
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
	private Product[] products = new Product[3];
	private String[] ids = { "i1", "i2", "i3", "i4", "i5", "i6" };
	private Cart[] cart = new Cart[6];

	public void GeneratateTestData() {
		// create members
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

	/**
	 * Tests Member creation.
	 */
	public void TestAddMember() {
		for (int count = 0; count < members.length; count++) {
			Request.instance().setMemberAddress(addresses[count]);
			Request.instance().setMemberName(names[count]);
			Result result = store.instance().addMember(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getMemberName().equals(names[count]);
			assert result.getMemberPhone().equals(phones[count]);
		}
	}

	public void TestAddProduct() {
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

	private void TestCheckOutCart() {
		GeneratateTestData();
		UI.retrieveProduct();
		UI.checkoutCart();
	}

	private void TestProcessShipment() {
		// TODO Auto-generated method stub

	}

	private void TestPrintTransactions() {
		// TODO Auto-generated method stub

	}

//	public void TestSearchMembership() {
//		Request.instance().setMemberId("M1");
//		// assert
//		// Library.instance().searchMembership(Request.instance()).getMemberId().equals("M1");
//		Request.instance().setMemberId("M4");
//		// assert Library.instance().searchMembership(Request.instance()) == null;
//	}

//	/**
//	 * Tests a list of Members
//	 */
//	public void TestListMembers() {
//		Iterator<Result> testMembers = store.listMembers();
//
//		while (testMembers.hasNext()) {
//			int i = 0;
//			Result actual = testMembers.next();
//			MemberList expected = MemberList.getInstance();
//			assert actual.getMemberId().equals(i);
//			assert actual.getMemberName().equals(names[i]);
//			assert actual.getMemberAddress().equals(addresses[i]);
//			i++;
//
//		}
//	}

//	/**
//	 * Tests a list of Products
//	 */
//	public void TestListProducts() {
//		Iterator<Result> testProducts = store.listProducts();
//
//		while (testProducts.hasNext()) {
//			int i = 0;
//			Result actual = testProducts.next();
//			Inventory expected = Inventory.getInstance();
//			assert actual.getProductId().equals(i);
//			assert actual.getProductName().equals(names[i]);
//			assert actual.getProductCurrentPrice().equals(price[i]);
//			assert actual.getProductMinimumReorderLevel().equals(reorderLevel[i]);
//			i++;
//
//		}
//
//	}

//	/**
//	 *Test a list of outstanding orders 
//	 */
//	public void TestListOrders() {
//		Iterator<Result> testOrders = store.listOrders();
//		Order order1 = new Order("P1", "Apple", 22);
//		Order order2 = new Order("P2", "Steak", 24);
//		Order order3 = new Order("P3", "Eggs", 26);
//		LinkedList<Order> orderList = new LinkedList<Order>();
//		orderList.add(order1);
//		orderList.add(order2);
//		orderList.add(order3);
//		while (testOrders.hasNext()) {
//			int i = 0;
//			Result actual = testOrders.next();
//			Order expected = orderList.get(i);
//			assert expected.getProductId().equals(actual.getOrderProductId());
//			assert expected.getProductName().equals(actual.getOrderProductName());
//			assert expected.getAmountOrdered().equals(actual.getAmountOrdered());
//			i++;
//		}

	public void TestAll() {
		TestAddMember();
		TestAddProduct();
		TestCheckOutCart();
		TestProcessShipment();
		TestPrintTransactions();

//		testSearchMembership();
//		testListMembers();
//		testListProducts();
//		testListOrders();
	}

	public static void main(String[] args) {

		new AutomatedTester().TestAll();
	}
}
