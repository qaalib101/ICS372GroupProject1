package edu.ics372.groupProject1.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;

import edu.ics372.groupProject1.collections.Inventory;
import edu.ics372.groupProject1.collections.MemberList;
import edu.ics372.groupProject1.collections.OrderList;
import edu.ics372.groupProject1.entities.CartItem;
import edu.ics372.groupProject1.entities.Member;
import edu.ics372.groupProject1.entities.Order;
import edu.ics372.groupProject1.entities.Product;
import edu.ics372.groupProject1.entities.Transaction;
import edu.ics372.groupProject1.iterators.SafeMemberIterator;
import edu.ics372.groupProject1.iterators.SafeOrderIterator;
import edu.ics372.groupProject1.iterators.SafeProductIterator;

/**
 * Class that represents the main system of the grocery store
 * 
 * 
 * @author Qaalib Farah, Ayden Sinn, Nate Goetsch, Leng Vang, John Quinlan
 *
 */

public class GroceryStore {
	private static final long serialVersionUID = 1L;
	private Inventory inventory = Inventory.getInstance();
	private MemberList members = MemberList.getInstance();
	private OrderList orders = OrderList.getInstance();
	private static GroceryStore store;

	/**
	 * Private for the singleton pattern Creates the catalog and member collection
	 * objects
	 */
	private GroceryStore() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static GroceryStore instance() {
		if (store == null) {
			return store = new GroceryStore();
		} else {
			return store;
		}
	}

	/**
	 * creates a member with the given parameters and adds it to members
	 * 
	 * @param name    name of the member
	 * @param address address of the member
	 * @param phone   phone number of the member
	 * @param date    date the member joins
	 * @param fee     amount member pays as fee
	 * @return true if the member was successfully created
	 */
	public Result enrollMember(Request request) {
		Result result = new Result();
		Double fee = Double.valueOf(request.getMemberFee());
		Member newMember = new Member(request.getMemberName(), request.getMemberAddress(), request.getMemberPhone(),
				request.getDate(), fee);
		if (members.insertMember(newMember)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setMemberFields(newMember);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * removes a member with the given id from members
	 * 
	 * @param id the member's id
	 * @return true if the member was successfully removed
	 */
	public Result removeMember(Request request) {
		Result result = new Result();
		if (members.removeMember(request.getMemberId())) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * creates a product with the given parameters and adds it to inventory
	 * 
	 * @param name         name of the product
	 * @param price        price of the product
	 * @param reorderLevel reorder level of the product
	 * @return true if the product was successfully created
	 */
	public Result addProduct(Request request) {
		Result result = new Result();
		Double price = Double.valueOf(request.getProductCurrentPrice());
		int reorderLevel = Integer.parseInt(request.getProductMinimumReorderLevel());
		Product newProduct = new Product(request.getProductName(), price, reorderLevel);
		if (inventory.insertProduct(newProduct)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setProductFields(newProduct);
			Order newOrder = new Order(newProduct.getId(), newProduct.getName(), reorderLevel * 2);
			orders.insertOrder(newOrder);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/*
	 * Checks out product. Adds item to the cart, collects the quantity from the
	 * cartItem updates the inventory level and if necessary reorders the item.
	 * 
	 * @param Request
	 * 
	 * @return Result
	 * 
	 */
	public Result checkOutProduct(Request request) {
		Result result = new Result();
		String productId = request.getProductId();
		String memberId = request.getMemberId();
		Member member = members.search(memberId);
		if (product == null) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			return result;
		} else {
			int productCartQuantity = Integer.parseInt(request.getProductCartQuantity());
			CartItem cartItem = new CartItem(product, productCartQuantity);
			if (!member.getCart().addCartItem(cartItem)) {
				result.setResultCode(Result.OPERATION_FAILED);
			}
			result.setResultCode(Result.OPERATION_COMPLETED);
			product.decrementQuantity(productCartQuantity);
			if (product.belowReorderLvL() && orders.search(productId) == null) {
				member.getCart().addToReorderList(product);
				Order newOrder = new Order(productId, product.getName(), Integer.parseInt(product.getReorderLevel()));
				orders.insertOrder(newOrder);
				StringBuilder productsToReorder = new StringBuilder("");
				for (int index = 0; index < member.getCart().getReorderList().size(); index++) {
					productsToReorder.append(member.getCart().getReorderList().get(index).getName() + "\n");
				}
				request.setProductsToBeReordered(productsToReorder);
			}
		}
		return result;
	}

	/*
	 * Prints cart checkout message, updates cart total, displays what has been
	 * reordered.
	 * 
	 * @param Request
	 * 
	 * @return void
	 * 
	 */
	public void printCheckOut(Request request) {
		String memberId = request.getMemberId();
		Member member = members.search(memberId);
		member.getCart().calculateCartTotal(member.getCart().getCartItems());
		request.setCartTotalPrice(String.valueOf(member.getCart().getTotalPrice()));
		String productName = "Product Name";
		String quantity = "Quantity";
		String unitPrice = "Unit Price";
		String total = "Total";
		String header = String.format("%30s %8s %11s   %5s ", productName, quantity, unitPrice, total);
		String itemDetails = "";
		System.out.println(header);
		for (int index = 0; index < member.getCart().getCartItems().size(); index++) {
			itemDetails = member.getCart().getCartItems().get(index).toString();
			System.out.println(itemDetails);
		}
		member.getCart().printCartTotal();
		if (request.getProductsToBeReordered().length() != 0) {
			System.out.println("The product(s), ");
			System.out.println(Request.instance().getProductsToBeReordered());
			System.out.println("are to be reordered.");
		}
		recoredTransaction(request);
	}

	/**
	 * Record the transaction to the transaction list
	 * 
	 * @param Request
	 * 
	 * @return void
	 */
	private void recoredTransaction(Request request) {
		String memberId = request.getMemberId();
		Member member = members.search(memberId);
		Transaction transaction = new Transaction(Integer.parseInt(request.getMemberId()),
				Double.parseDouble(request.getCartTotalPrice()));
		member.transactionList().add(transaction);
	}

	/**
	 * Retrieves product info by name
	 * 
	 * @param Request
	 * 
	 * @return Result
	 */
	public Result retrieveProduct(Request request) {
		Result result = new Result();
		String productName = request.getProductName();
		Product product = inventory.searchByName(productName);

		if (product != null) {
			request.setProductId(product.getId());
			request.setProductCurrentPrice(product.getPrice());
			request.setProductQuantity(product.getQuantity());
			result.setResultCode(Result.OPERATION_COMPLETED);
		} else {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
		}
		return result;
	}

	/**
	 * Method to process orders in the form of shipments.
	 * 
	 * @param request Request object with shipment information
	 * @return Result object with product information and restocked quantity
	 */
	public Result processShipment(Request request) {
		Result result = new Result();
		String productId = request.getProductId();
		Product product = inventory.search(productId);
		if (product == null) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			return result;
		}
		Order order = orders.search(productId);
		if (order == null) {
			result.setResultCode(Result.NO_OUTSTANDING_ORDER);
			return result;
		}
		if (product.restock(Integer.parseInt(order.getAmountOrdered())) && orders.removeOrder(productId)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setProductFields(product);
		} else {
			result.setResultCode(Result.OPERATION_FAILED);
		}
		return result;
	}

	/**
	 * Method to process a product price change.
	 * 
	 * @param request Request object with product information
	 * @return Result object with product information and restocked quantity
	 */
	public Result changePrice(Request request) {
		Result result = new Result();
		Product product = inventory.search(request.getProductId());
		if (product == null) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			return result;
		}
		if (product.changePrice(request.getProductCurrentPrice())) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setProductFields(product);
		} else {
			result.setResultCode(Result.OPERATION_FAILED);
		}
		return result;
	}

	/**
	 * Method to get transactions for a specific member
	 * 
	 * @param request
	 * @return
	 */
	public Iterator<Result> getTransactions(Request request) {
		Member member = members.search(request.getMemberId());
		if (member == null) {
			return new LinkedList<Result>().iterator();
		}
		return member.getTransactionsBetweenDates(request.getStartDate(), request.getEndDate());
	}

	/**
	 * Returns an iterator to Order info. The Iterator returns only copies of the
	 * Member fields and are assembled into the objects returned via next().
	 * 
	 * @return an Iterator to Result - only the Member fields are valid.
	 */
	public Iterator<Result> listMembers() {
		return new SafeMemberIterator(members.iterator());
	}

	/**
	 * Returns an iterator to Product info. The Iterator returns copies of the
	 * Product fields and are assembled into the objects returned via next().
	 * 
	 * @return an Iterator to Result - only the Book fields are valid.
	 */
	public Iterator<Result> listProducts() {
		return new SafeProductIterator(inventory.iterator());
	}

	/**
	 * Returns an iterator to Order info. The Iterator returns copies of the Order
	 * fields are assembled into the objects returned via next().
	 * 
	 * @return an Iterator to Result - only the Book fields are valid.
	 */
	public Iterator<Result> listOrders() {
		return new SafeOrderIterator(orders.iterator());
	}

	/**
	 * Retrieves a deserialized version of the library from disk
	 * 
	 * @return a GroceryStore object
	 */
	public static GroceryStore retrieve() {
		try {
			FileInputStream file = new FileInputStream("GroceryStoreData");
			ObjectInputStream input = new ObjectInputStream(file);
			store = (GroceryStore) input.readObject();
//			Member.retrieve(input);
			return store;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

	/**
	 * Searches for a given member
	 * 
	 * @param memberId id of the member
	 * @return true iff the member is in the member list collection
	 */
	public Result searchMembership(Request request) {
		Result result = new Result();
		Member member = members.search(request.getMemberId());
		if (member == null) {
			result.setResultCode(Result.NO_SUCH_MEMBER);
		} else {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setMemberFields(member);
		}
		return result;
	}

	/**
	 * Serializes the Library object
	 * 
	 * @return true iff the data could be saved
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("CooperativeData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(store);
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	@Override
	public String toString() {
		return inventory + "\n" + members;
	}

}
