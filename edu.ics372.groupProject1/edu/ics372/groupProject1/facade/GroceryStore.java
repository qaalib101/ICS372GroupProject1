package edu.ics372.groupProject1.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.ics372.groupProject1.collections.Inventory;
import edu.ics372.groupProject1.collections.MemberList;
import edu.ics372.groupProject1.collections.OrderList;
import edu.ics372.groupProject1.entities.Member;
import edu.ics372.groupProject1.entities.Order;
import edu.ics372.groupProject1.entities.Product;
import edu.ics372.groupProject1.iterators.SafeMemberIterator;
import edu.ics372.groupProject1.iterators.SafeOrderIterator;
import edu.ics372.groupProject1.iterators.SafeProductIterator;

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

	public Result addMember(Request request) {
		Result result = new Result();
		Member newMember = new Member(request.getMemberName(), request.getMemberAddress(), request.getMemberPhone(),
				request.getMemberDate(), request.getMemberFee());
		boolean addMember = members.insertMember(newMember);
		if (addMember) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setMemberFields(newMember);
		}
		return result;
	}

	public Result removeMember(Request request) {
		Result result = new Result();
		boolean memberExist = members.removeMember(request.getMemberId());
		if (memberExist) {
			result.setResultCode(Result.OPERATION_COMPLETED);
		} else {
			result.setResultCode(Result.OPERATION_FAILED);
		}
		return result;
	}

	public Result retrieveMember(Request request) {
		Result result = new Result();
		Member member = members.searchByName(request.getMemberName());
		if (member != null) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setMemberFields(member);
		} else {
			result.setResultCode(Result.OPERATION_FAILED);
		}
		return result;
	}

	// Adds a new product to the inventory
	public Result addProduct(Request request) {
		Result result = new Result();
		Product newProduct = new Product(request.getProductName(), request.getProductPrice(),
				request.getProductMinimumReorderLevel());
		boolean addProduct = inventory.insertProduct(newProduct);
		if (addProduct) {
			result.setResultCode(result.OPERATION_COMPLETED);
			result.setProductFields(newProduct);
		}
		// I'm a little confused about how ordering will be implemented
//		Order(name, price, reorderLevel * 2);
		return result;
	}

	public Result checkoutCart(Request request) {
		Result result = new Result();
		return result;
	}

	public Result retrieveProduct(Request request) {
		Result result = new Result();
		Product product = inventory.searchByName(request.getProductName());
		if (product != null) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setProductFields(product);
		} else {
			result.setResultCode(Result.OPERATION_FAILED);
		}
		return result;
	}

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
		if (product.restock(Integer.parseInt(order.getAmount())) && orders.removeOrder(productId)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setProductFields(product);
		} else {
			result.setResultCode(Result.OPERATION_FAILED);
		}
		return result;
	}

	public Result changePrice(Request request) {
		Result result = new Result();
		Product product = inventory.search(request.getProductId());
		if (product == null) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			return result;
		}
		if (product.changePrice(request.getProductPrice())) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setProductFields(product);
		} else {
			result.setResultCode(Result.OPERATION_FAILED);
		}
		return result;
	}

	public Iterator<Result> getTransactions(Request request) {
		Member member = members.search(request.getMemberId());
		if (member == null) {
			return new LinkedList<Result>().iterator();
		}
		return member.getTransactionsBetweenDates(request.getStartDate(), request.getEndDate());
	}

	/**
	 * Returns an iterator to Member info. The Iterator returned is a safe one, in
	 * the sense that only copies of the Member fields are assembled into the
	 * objects returned via next().
	 * 
	 * @return an Iterator to Result - only the Member fields are valid.
	 */
	public Iterator<Result> getMembers() {
		return new SafeMemberIterator(members.iterator());
	}

	/**
	 * Returns an iterator to Product info. The Iterator returned is a safe one, in
	 * the sense that only copies of the Product fields are assembled into the
	 * objects returned via next().
	 * 
	 * @return an Iterator to Result - only the Product fields are valid.
	 */
	public Iterator<Result> getProducts() {
		return new SafeProductIterator(inventory.iterator());
	}

	/**
	 * Returns an iterator to Orders info. The Iterator returned is a safe one, in
	 * the sense that only copies of the Order fields are assembled into the objects
	 * returned via next().
	 * 
	 * @return an Iterator to Result - only the Order fields are valid.
	 */
	public Iterator<Result> getOrders() {
		return new SafeOrderIterator(orders.iterator());
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

	@Override
	public String toString() {
		return inventory + "\n" + members;
	}

}
