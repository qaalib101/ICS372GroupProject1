//testing
//second comment

package edu.ics372.groupProject1.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import edu.ics372.groupProject1.facade.GroceryStore;
import edu.ics372.groupProject1.facade.Request;
import edu.ics372.groupProject1.facade.Result;

/**
 * 
 * This class implements the user interface for the Cooperative project. The
 * commands are encoded as integers using a number of static final variables. A
 * number of utility methods exist to make it easier to parse the input.
 *
 */
public class UserInterface {
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static GroceryStore store;
	private static final DecimalFormat decimalFormat = new DecimalFormat("#.00");
	private static final int EXIT = 0;
	private static final int ENROLL_MEMBER = 1;
	private static final int REMOVE_MEMBER = 2;
	private static final int RETRIEVE_MEMBER = 3;
	private static final int ADD_PRODUCT = 4;
	private static final int CHECKOUT_CART = 5;
	private static final int RETRIEVE_PRODUCT = 6;
	private static final int PROCESS_SHIPMENT = 7;
	private static final int CHANGE_PRICE = 8;
	private static final int PRINT_TRANSACTIONS = 9;
	private static final int LIST_MEMBERS = 10;
	private static final int LIST_PRODUCTS = 11;
	private static final int LIST_ORDERS = 12;
	private static final int SAVE = 13;
	private static final int RETRIEVE = 14;
	private static final int HELP = 15;

	/**
	 * Made private for singleton pattern. Conditionally looks for any saved data.
	 * Otherwise, it gets a singleton Cooperative object.
	 */
	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			if (yesOrNo("Do you want to generate a test bed and invoke the functionality using asserts?")) {
				System.out.println("To be implemented");
				store = GroceryStore.instance();
			} else {
				store = GroceryStore.instance();
			}
		}

	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	/**
	 * Gets a token after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Gets a name after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getName(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				return line;
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);

	}

	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 * 
	 * @param prompt The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 * 
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * Converts the string to a number
	 * 
	 * @param prompt the string for prompting
	 * @return the integer corresponding to the string
	 * 
	 */
	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	/**
	 * Prompts for a date and gets a date object
	 * 
	 * @param prompt the prompt
	 * @return the data as a Calendar object
	 */
	public Calendar getDate(String prompt) {
		do {
			try {
				Calendar date = new GregorianCalendar();
				String item = getToken(prompt);
				DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
				date.setTime(dateFormat.parse(item));
				return date;
			} catch (Exception fe) {
				System.out.println("Please input a date as mm/dd/yy");
			}
		} while (true);
	}

	/**
	 * Converts the string to a double
	 * 
	 * @param prompt the string for prompting
	 * @return the integer corresponding to the string
	 * 
	 */
	public double getDoubleNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Double number = Double.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a double number ");
			}
		} while (true);
	}

	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 * 
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Displays the help screen
	 * 
	 */
	public void help() {
		System.out.println("Enter a number between 0 and 12 as explained below:");
		System.out.println(EXIT + " to Exit\n");
		System.out.println(ENROLL_MEMBER + " to add a member");
		System.out.println(REMOVE_MEMBER + " to  add books");
		System.out.println(RETRIEVE_MEMBER + " to  issue books to a  member");
		System.out.println(ADD_PRODUCT + " to  return books ");
		System.out.println(CHECKOUT_CART + " to  renew books ");
		System.out.println(RETRIEVE_PRODUCT + " to  remove books");
		System.out.println(PROCESS_SHIPMENT + " to  place a hold on a book");
		System.out.println(CHANGE_PRICE + " to  remove a hold on a book");
		System.out.println(PRINT_TRANSACTIONS + " to  process holds");
		System.out.println(LIST_MEMBERS + " to  print transactions");
		System.out.println(LIST_PRODUCTS + " to  print all members");
		System.out.println(LIST_ORDERS + " to  print all books");
		System.out.println(SAVE + " to  save data");
		System.out.println(RETRIEVE + " to  retrieve data");
		System.out.println(HELP + " for help");
	}

	/**
	 * Method to be called to process a shipment.
	 */
	private void processShipment() {
		do {
			Request.instance().setProductId(getToken("Enter product Id: "));
			Result result = store.processShipment(Request.instance());
			switch (result.getResultCode()) {
			case Result.PRODUCT_NOT_FOUND:
				System.out.println("No product with id " + Request.instance().getProductId());
				break;
			case Result.NO_OUTSTANDING_ORDER:
				System.out.println(
						"No outstanding order exists for product with id " + Request.instance().getProductId());
				break;
			case Result.OPERATION_FAILED:
				System.out.println("Order failed to process for product with id " + Request.instance().getProductId());
				break;
			case Result.OPERATION_COMPLETED:
				System.out.println("Order processed successfully\n" + "Product Id: " + result.getProductId() + "\n"
						+ "Product Name: " + result.getProductName() + "\n" + "New Stock Amount: "
						+ result.getProductQuantity());
				break;
			}
			if (!yesOrNo("Process more shipments?")) {
				break;
			}
		} while (true);

	}

	public void changePrice() {
		Request.instance().setProductId(getToken("Enter product Id: "));
		Request.instance().setProductCurrentPrice(
				decimalFormat.format(getDoubleNumber("Enter new product price (with two ending decimal places): ")));
		Result result = store.changePrice(Request.instance());
		switch (result.getResultCode()) {
		case Result.PRODUCT_NOT_FOUND:
			System.out.println("No product with id " + Request.instance().getProductId());
			break;
		case Result.OPERATION_FAILED:
			System.out.println("Changing price failed for product with id " + Request.instance().getProductId());
			break;
		case Result.OPERATION_COMPLETED:
			System.out
					.println("Changed price of " + result.getProductName() + " to $" + result.getProductCurrentPrice());
			break;
		}
	}
	
	/**
	 * Displays all members
	 */
	public void getMembers() {
		Iterator<Result> iterator = store.getMembers();
		System.out.println("List of Members (name, id, address)");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(result.getMemberName() + " " + result.getMemberId() + " " + result.getMemberAddress());
					
		}
		System.out.println("End of listing");
	}

	/**
	 * Gets and prints all books.
	 */
	public void getProducts() {
		Iterator<Result> iterator = store.getProducts();
		System.out.println("List of Prodcuts (name, id, minimum reorder level)");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(result.getProductName() + " " + result.getProductId() + " " 
					+ result.getProductMinimumReorderLevel());
					
		}
		System.out.println("End of listing");
	}
	
	public void getOrders() {
		Iterator<Result> iterator = store.getOrders();
		System.out.println("List of Orders (name, id, amount ordered)");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(result.getOrderProductName() + " " + result.getOrderProductId() + " " 
					+ result.getAmountOrdered());
					
		}
		System.out.println("End of listing");
	}

	/**
	 * Method to be called for saving the coop object. Uses the appropriate
	 * Cooperative method for saving.
	 * 
	 */
	private void save() {
		if (store.save()) {
			System.out.println(" The library has been successfully saved in the file CooperativeData \n");
		} else {
			System.out.println(" There has been an error in saving \n");
		}
	}

	/**
	 * Method to be called for retrieving saved data. Uses the appropriate
	 * Cooperative method for retrieval.
	 * 
	 */
	private void retrieve() {
		try {
			if (store == null) {
				store = GroceryStore.retrieve();
				if (store != null) {
					System.out.println(" The library has been successfully retrieved from the file CooperativeData \n");
				} else {
					System.out.println("File doesnt exist; creating new coop");
					store = GroceryStore.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	// add methods for business processes
	public void process() {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ENROLL_MEMBER:
				break;
			case REMOVE_MEMBER:
				break;
			case RETRIEVE_MEMBER:
				break;
			case ADD_PRODUCT:
				break;
			case CHECKOUT_CART:
				break;
			case RETRIEVE_PRODUCT:
				break;
			case PROCESS_SHIPMENT:
				processShipment();
				break;
			case CHANGE_PRICE:
				break;
			case PRINT_TRANSACTIONS:
				break;
			case LIST_MEMBERS:
				break;
			case LIST_PRODUCTS:
				break;
			case LIST_ORDERS:
				break;
			case RETRIEVE:
				break;
			case SAVE:
				save();
				break;
			case HELP:
				help();
				break;
			}
		}
	}

	/**
	 * The method to start the application. Simply calls process().
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		UserInterface.instance().process();
	}
}