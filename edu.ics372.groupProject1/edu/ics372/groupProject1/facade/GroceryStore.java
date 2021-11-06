package edu.ics372.groupProject1.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import edu.ics372.groupProject1.collections.Inventory;
import edu.ics372.groupProject1.collections.MemberList;
import edu.ics372.groupProject1.collections.OrderList;
import edu.ics372.groupProject1.entities.CartItem;
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
	 * creates a member with the given parameters and adds it to members
	 * 
	 * @param name    name of the member
	 * @param address address of the member
	 * @param phone   phone number of the member
	 * @param date    date the member joins
	 * @param fee     amount member pays as fee
	 * @return true if the member was successfully created
	 */
	public boolean addMember(String name, String address, String phone, String date, double fee) {
		Member newMember = new Member(name, address, phone, date, fee);
		boolean result = members.insertMember(newMember);
		return result;
	}

	/**
	 * removes a member with the given id from members
	 * 
	 * @param id the member's id
	 * @return true if the member was successfully removed
	 */
	public boolean removeMember(String id) {
		return members.removeMember(id);
	}

	/**
	 * creates a product with the given parameters and adds it to inventory
	 * 
	 * @param name         name of the product
	 * @param price        price of the product
	 * @param reorderLevel reorder level of the product
	 * @return true if the product was successfully created
	 */
	public boolean addProduct(String name, double price, int reorderLevel) {
		Product newProduct = new Product(name, price, reorderLevel);
		boolean result = inventory.insertProduct(newProduct);
		// I'm a little confused about how ordering will be implemented
//		Order(name, price, reorderLevel * 2);
		return result;
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

	/*
	 * Checks out product. Adds item to the cart, collects the quantity from the
	 * cartItem updates the inventory level and if necessary reorders the item.
	 * 
	 * @param productId of the product
	 * 
	 * @return true if request is completed
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
			if (product.autoRestock() && orders.search(productId) == null) {
				member.getCart().addToReorderList(product);
				Order newOrder = new Order(productId, product.getName(), Integer.parseInt(product.getReorderLevel()));
				orders.insertOrder(newOrder);
				buildReorderList(request);
			}
		}
		return result;
	}

	private void buildReorderList(Request request) {
		String memberId = request.getMemberId();
		Member member = members.search(memberId);
		StringBuilder productsToReorder = new StringBuilder("");
		for (int index = 0; index < member.getCart().getReorderList().size(); index++) {
			productsToReorder.append(member.getCart().getReorderList().get(index).getName() + "\n");
		}
		request.setProductsToBeReordered(productsToReorder);
	}

	/**
	 * Retrieves product info by name
	 * 
	 * @param product name
	 * 
	 * @return result code
	 */
	public Result retrieveProductInfo(Request request) {
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

	/*
	 * Calculates carts total
	 * 
	 * @param memberId
	 * 
	 * @return true if request is completed
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
	 * @param memberId
	 * 
	 * @return true if request is completed
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

	@Override
	public String toString() {
		return inventory + "\n" + members;
	}
}
