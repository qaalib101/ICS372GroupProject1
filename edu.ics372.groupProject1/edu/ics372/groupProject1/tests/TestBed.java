package edu.ics372.groupProject1.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

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
public class TestBed {
	private GroceryStore store;
	private UserInterface UI;
	private int[] months = { 2, 1, 2, 1, 9 };
	private int[] days = { 1, 22, 22, 1, 21 };
	private int[] years = { 1955, 2005, 1926, 2001, 1311 };
	private Calendar[] dates = new GregorianCalendar[5];
	private String[] names = { "name1", "name2", "name3", "name4", "name5" };
	private String[] addresses = { "address1", "address2", "address3", "address4", "address5" };
	private String[] phones = { "phone1", "phone2", "phone3", "phone4", "phone5" };
	private Member[] members = new Member[5];
	private String[] storeItems = { "product1", "product2", "product3", "product4", "product5", "product6", "product7",
			"product8", "product9", "product10", "product11", "product12", "product13", "product14", "product15",
			"product16", "product17", "product18", "product19", "product20" };
	private double[] price = { 1.50, 3.25, 6.00, 3.26, 2.25, 1.00, 3.11, 1.01, 1.33, 1.45, 6.55, 5.55, 4.25, 0.88, 1.13,
			2.22, 13.99, 2.45, 1.75, 3.69 };
	private int[] reorderLevel = { 20, 20, 20, 20, 20, 30, 15, 15, 15, 10, 10, 15, 20, 20, 15, 16, 25, 14, 30, 15 };
	private Product[] products = new Product[20];
	private String[] ids = { "id1", "id2", "id3", "id4", "id5", "id6" };
	private DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");

	/**
	 * Tests Member creation.
	 */
	public void TestAddMember() {
		// create dates
		for (int index = 0; index < years.length; index++) {
			Calendar date = new GregorianCalendar(years[index], months[index], days[index]);
			dates[index] = date;
		}

		for (int count = 0; count < members.length; count++) {
			Request.instance().setMemberAddress(addresses[count]);
			Request.instance().setMemberName(names[count]);
			Request.instance().setDate(dates[count]);
			Request.instance().setMemberFee("10");
			Result result = GroceryStore.instance().enrollMember(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getMemberName().equals(names[count]);
			assert result.getMemberPhone().equals(phones[count]);
			assert result.getMemberDate().equals(dates[count].get(Calendar.MONTH) + "/"
					+ dates[count].get(Calendar.DATE) + "/" + dates[count].get(Calendar.YEAR));
			assert result.getMemberFee() == 10;
		}
	}

	/**
	 * Tests Product creation.
	 */
	public void TestAddProduct() {
		for (int count = 0; count < products.length; count++) {
			Request.instance().setProductPrice(Double.toString(price[count]));
			Request.instance().setProductName(storeItems[count]);
			Request.instance().setProductMinimumReorderLevel(Integer.toString(reorderLevel[count]));
			Result result = GroceryStore.instance().addProduct(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(storeItems[count]);
			assert result.getProductPrice().equals(price[count]);
			assert result.getProductMinimumReorderLevel() == reorderLevel[count];
		}
	}

	private void TestCheckOutCart() {

	}

	private void TestProcessShipment() {
		// TODO Auto-generated method stub
		for (int count = 0; count < products.length; count++) {
			Request.instance().setProductId("P" + ++count);
			Result result = GroceryStore.instance().processShipment(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(storeItems[count]);
			assert result.getProductPrice().equals(price[count]);
			assert result.getProductQuantity().equals(Double.toString(2 * result.getProductMinimumReorderLevel()));
		}
	}

	private void TestPrintTransactions() {
		// TODO Auto-generated method stub
	}

	private void TestListOrders() {
		int i = 1;
		for (Iterator<Result> orderList = GroceryStore.instance().listOrders(); orderList.hasNext();) {
			Result result = (Result) orderList.next();
			assert result.getOrderProductName().equals(storeItems[i]);
			assert result.getOrderProductId().equals("P" + i);
			assert result.getAmountOrdered().equals(Integer.toString(reorderLevel[i]));
		}
	}

	public void TestAll() {
		TestAddMember();
		TestAddProduct();
		// TestCheckOutCart();
		TestListOrders();
		TestProcessShipment();
		// TestPrintTransactions();
	}

	public static void main(String[] args) {

		new TestBed().TestAll();
	}
}
