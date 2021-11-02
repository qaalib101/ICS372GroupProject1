package edu.ics372.groupProject1.tests;

import edu.ics372.groupProject1.facade.GroceryStore;

public class AutomatedTester {
	private GroceryStore store;

	public void testProcessShipment() {

	}

	// implement
	private void testCheckOutCart() {

	}

	public void testAll() {
		testProcessShipment();
		testCheckOutCart();
	}

	public static void main(String[] args) {
		new AutomatedTester().testAll();
	}
}
