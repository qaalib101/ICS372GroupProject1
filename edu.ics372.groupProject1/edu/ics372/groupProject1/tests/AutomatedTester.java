package edu.ics372.groupProject1.tests;

import edu.ics372.groupProject1.facade.GroceryStore;
import edu.ics372.groupProject1.ui.UserInterface;

public class AutomatedTester {
	private GroceryStore store;
	private UserInterface UI;

	public void testProcessShipment() {

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

	public void testAll() {

		testProcessShipment();
		testCheckOutCart();
	}

	public static void main(String[] args) {

		new AutomatedTester().testAll();
	}
}
