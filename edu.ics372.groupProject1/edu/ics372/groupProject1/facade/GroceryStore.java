package groupProject1.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator; //Remove this
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
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setProductFields(newProduct);
		}
		return result;
	}

	/**
	 * CODE FOR CHECKING OUT CART
	 */

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
		Product product = inventory.search(productId);
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
			if (product.autoRestock() && orders.search(productId) == null) { // Null pointer exception is occuring
				member.getCart().addToReorderList(product);
				Order newOrder = new Order(productId, product.getName(), Integer.parseInt(product.getReorderLevel()));
				orders.insertOrder(newOrder);
				buildReorderList(request);
			}
		}
		return result;
	}

	/**
	 * Builds StringBuilder list of products to be reordered during checkout
	 * process.
	 * 
	 * @param Request
	 * @return void
	 * 
	 */
	private void buildReorderList(Request request) {
		String memberId = request.getMemberId();
		Member member = members.search(memberId);
		StringBuilder productsToReorder = new StringBuilder("");
		for (int index = 0; index < member.getCart().getReorderList().size(); index++) {
			productsToReorder.append(member.getCart().getReorderList().get(index).getName() + "\n");
		}
		request.setProductsToBeReordered(productsToReorder);
	}

	/*
	 * Calculates carts total
	 * 
	 * @param Request
	 * 
	 * @return Result
	 * 
	 */
	public Result calculateCartTotalPrice(Request request) {
		Result result = new Result();
		String memberId = request.getMemberId();
		Member member = members.search(memberId);
		member.getCart().calculateCartTotal(member.getCart().getCartItems());
		request.setCartTotalPrice(String.valueOf(member.getCart().getTotalPrice()));
		result.setResultCode(Result.OPERATION_COMPLETED);
		return result;

	}

	/*
	 * Prints cart checkout message
	 * 
	 * @param Request
	 * 
	 * @return void
	 * 
	 */
	public void printCheckOut(Request request) {
		String productName = "Product Name";
		String quantity = "Quantity";
		String unitPrice = "Unit Price";
		String total = "Total";
		String header = String.format("%30s %8s %11s   %5s ", productName, quantity, unitPrice, total);

		String itemDetails = "";
		String memberId = request.getMemberId();
		Member member = members.search(memberId);
		System.out.println(header);
		for (int index = 0; index < member.getCart().getCartItems().size(); index++) {
			itemDetails = member.getCart().getCartItems().get(index).printItemDetails();
			System.out.println(itemDetails);
		}
		member.getCart().printCartTotal();
		member.addTransaction(new Transaction((Double.parseDouble(request.getCartTotalPrice()))));// ADDED TRANSACTION
																									// TO MEMBER's
																									// transactions
	}

	/**
	 * CODE FOR CHECKING OUT CART
	 */

	/**
	 * Retrieves product info by name
	 * 
	 * @param Request
	 * 
	 * @return Result
	 */
	public Result retrieveProductInfo(Request request) {
		Result result = new Result();
		String productName = request.getProductName();
		Product product = inventory.searchByName(productName);

		if (product != null) {
			request.setProductId(product.getId());
			request.setProductPrice(product.getPrice());
			request.setProductQuantity(product.getQuantity());
			result.setResultCode(Result.OPERATION_COMPLETED);
		} else {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
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

	/*
	 * This method calls on addTransaction from the Member class to add a
	 * transaction to a given Member
	 * 
	 * @return void
	 */
	public void addTransactionToMember(Request request) {
		Member member = members.search(request.getMemberId());
		member.addTransaction(new Transaction(Double.parseDouble(request.getCartTotalPrice())));
	}

	/*
	 * Returns an iterator to Member. The iterator returned is a safe iterator, and
	 * gets all the transactions of that member between a start and end date range.
	 * 
	 * @return void
	 */
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
			Member.save(output);
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
			Member.retrieve(input);
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
