package groupProject1.tests;

import groupProject1.entities.Cart;
import groupProject1.entities.CartItem;
import groupProject1.entities.Member;
import groupProject1.entities.Product;
import groupProject1.facade.GroceryStore;
import groupProject1.facade.Request;
import groupProject1.facade.Result;

/**
 * This class generates sample automated tests for the library system using
 * asserts.
 * 
 * @author Brahma Dathan
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
	private String[] authors = { "a1", "a2", "a3", "a4", "a5", "a6" };
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
			Request.instance().setProductPrice(price[count]);
			Request.instance().setProductName(storeItems[count]);
			Request.instance().setProductMinimumReorderLevel(reorderLevel[count]);
			Result result = store.instance().addProduct(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(storeItems[count]);
			assert result.getProductPrice().equals(price[count]);
			assert result.getProductPrice().equals(reorderLevel[count]);
		}
	}

	public void testAddItemToCart() {
		Cart cart = new Cart();
		for (int numberOfItems = 0; numberOfItems < 5; numberOfItems++) {
			Product newProduct = new Product(storeItems[numberOfItems], numberOfItems % 1.5 + 0.25,
					numberOfItems % 3 + 1);
			CartItem cartItem = new CartItem(newProduct, numberOfItems % 2 + 1);
			// Result result = store.instance().
		}

	}

	public void testSearchMembership() {
		Request.instance().setMemberId("M1");
		// assert
		// Library.instance().searchMembership(Request.instance()).getMemberId().equals("M1");
		Request.instance().setMemberId("M4");
		// assert Library.instance().searchMembership(Request.instance()) == null;
	}

	public void testAll() {
		testAddMember();
		testAddProduct();
		testSearchMembership();
	}

	public static void main(String[] args) {
		new AutomatedTester().testAll();
	}
}
