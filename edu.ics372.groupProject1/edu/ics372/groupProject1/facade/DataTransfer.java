package edu.ics372.groupProject1.facade;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import edu.ics372.groupProject1.entities.Member;
import edu.ics372.groupProject1.entities.Order;
import edu.ics372.groupProject1.entities.Product;
import edu.ics372.groupProject1.entities.Transaction;

/**
 * The DataTransfer class is used to transfer data between the UserInterface and
 * the GroceryStore class. It also supports iteration for Member, Product and
 * Order class by copying their fields and sending that information in either
 * direction.
 * 
 * @author Qaalib Farah, Ayden Sinn, Nate Goetsch, Leng Vang, John Quinlan
 */
public class DataTransfer {
	private String memberId;
	private String memberName;
	private String memberAddress;
	private String memberPhone;
	private String memberDate;

	private String memberFee;
	private String productId;
	private String productName;
	private String productPrice;
	private String productMinimumReorderLevel;
	private String productQuantity;
	private String orderProductName;
	private String orderProductId;
	private String amountOrdered;
	private Calendar transactionDate;
	private String transactionTotalPrice;
	private String productCartQuantity;
	private String cartTotalPrice;
	private String checkoutLineItem;
	private String isProductReordered;
	private StringBuilder productsToBeReordered;

	/**
	 * Resets all fields
	 */
	public DataTransfer() {
		reset();
	}

	/**
	 * getter for member ID
	 * 
	 * @return memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * setter for memberID
	 * 
	 * @param memberID
	 */
	public void setMemberId(String memberID) {
		this.memberId = memberID;
	}

	/**
	 * getter for memberName
	 * 
	 * @return memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * setter for memberName
	 * 
	 * @param memberName
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * getter for memberAddress
	 * 
	 * @return memberAddress
	 */
	public String getMemberAddress() {
		return memberAddress;
	}

	/**
	 * setter for memberAddress
	 * 
	 * @param memberAddress
	 */
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	/**
	 * getter for memberPhone
	 * 
	 * @return memberPhone
	 */
	public String getMemberPhone() {
		return memberPhone;
	}

	/**
	 * setter for memberPhone
	 * 
	 * @param memberPhone
	 */
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	/**
	 * getter for memberDate
	 * 
	 * @return memberDate
	 */
	public String getMemberDate() {
		return memberDate;
	}

	/**
	 * setter for memberDate
	 * 
	 * @param memberDate
	 */
	public void setMemberDate(String memberDate) {
		this.memberDate = memberDate;
	}

	/**
	 * getter for memberFee
	 * 
	 * @return memberFee
	 */
	public double getMemberFee() {
		return Double.valueOf(memberFee);
	}

	/**
	 * setter for memberFee
	 * 
	 * @param memberFee
	 */
	public void setMemberFee(String memberFee) {
		this.memberFee = memberFee;

	}

	/**
	 * Sets all member fields using the member parameter
	 * 
	 * @param member whose fields should be copied
	 */
	public void setMemberFields(Member member) {
		setMemberId(member.getId());
		setMemberName(member.getName());
		setMemberAddress(member.getAddress());
		setMemberPhone(member.getPhone());
		setMemberDate(member.getDate());
		setMemberFee(member.getFee());
	}

	/**
	 * getter for productID
	 * 
	 * @return productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * setter for productID
	 * 
	 * @param productId
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * getter for productName
	 * 
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * setter for productName
	 * 
	 * @param productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * getter for productPrice
	 * 
	 * @return productPrice
	 */
	public Double getProductPrice() {
		return Double.valueOf(productPrice);
	}

	/**
	 * setter for productPrice
	 * 
	 * @param productPrice
	 */
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * getter for a product's minimum reorder level
	 * 
	 * @return productMinimumReorderLevel
	 */
	public int getProductMinimumReorderLevel() {
		return Integer.parseInt(productMinimumReorderLevel);
	}

	/**
	 * setter for a product's minimum reorder level
	 * 
	 * @param productMinimumReorderLevel
	 */
	public void setProductMinimumReorderLevel(String productMinimumReorderLevel) {
		this.productMinimumReorderLevel = productMinimumReorderLevel;
	}

	/**
	 * getter for a product's quantity
	 * 
	 * @return productQuantity
	 */
	public String getProductQuantity() {
		return productQuantity;
	}

	/**
	 * sets all product fields using the product parameter
	 * 
	 * @param product whose fields should be copied
	 * 
	 */
	public void setProductFields(Product product) {
		setProductId(product.getId());
		setProductName(product.getName());
		setProductQuantity(product.getQuantity());
		setProductPrice(product.getPrice());
		setProductMinimumReorderLevel(product.getReorderLevel());
	}

	/**
	 * getter for an order's product name
	 * 
	 * @return orderProductName
	 */
	public String getOrderProductName() {
		return orderProductName;
	}

	/**
	 * setter for an order's product name
	 * 
	 * @param orderProductName
	 */
	public void setOrderProductName(String orderProductName) {
		this.orderProductName = orderProductName;
	}

	/**
	 * getter for an order's product ID
	 * 
	 * @return orderProductId
	 */
	public String getOrderProductId() {
		return orderProductId;
	}

	/**
	 * setter for an order's product ID
	 * 
	 * @param orderProductId
	 */
	public void setOrderProductId(String orderProductId) {
		this.orderProductId = orderProductId;
	}

	/**
	 * setter for a product's quantity
	 * 
	 * @param productQuantity
	 */
	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	/**
	 * getter for an order's amount
	 * 
	 * @return amountOrdered
	 */
	public String getAmountOrdered() {
		return amountOrdered;
	}

	/**
	 * setter for an order's amount
	 * 
	 * @param orderAmountOrdered
	 */
	public void setAmountOrdered(String orderAmountOrdered) {
		this.amountOrdered = orderAmountOrdered;
	}

	/**
	 * sets all order fields using the order parameter
	 * 
	 * @param product whose fields should be copied
	 */
	public void setOrderFields(Order order) {
		orderProductId = order.getProductId();
		orderProductName = order.getProductName();
		amountOrdered = order.getAmountOrdered();
	}

	/**
	 * getter for a transaction's total price
	 * 
	 * @return transactionTotalPrice
	 */
	public String getTransactionTotalPrice() {
		return transactionTotalPrice;
	}

	/**
	 * setter for a transaction's total price
	 * 
	 * @param transactionTotalPrice
	 */
	public void setTransactionTotalPrice(String transactionTotalPrice) {
		this.transactionTotalPrice = transactionTotalPrice;
	}

	/**
	 * getter for a transaction's date
	 * 
	 * @return transactionDate
	 */
	public String getTransactionDate() {
		DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		return dateFormat.format(transactionDate);
	}

	/**
	 * setter for a transaction's date
	 * 
	 * @param transactionDate
	 */
	public void setTransactionDate(Calendar transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * sets all transaction fields using the transaction parameter
	 * 
	 * @param transaction whose fields should be copied
	 */
	public void setTransactionFields(Transaction transaction) {
		setTransactionDate(transaction.returnDate());
		setTransactionTotalPrice(transaction.getTotalPrice());
	}

	/**
	 * Sets all fields to "Product fields"
	 */
	public StringBuilder getProductsToBeReordered() {
		return productsToBeReordered;
	}

	/**
	 * setter for products to be reordered
	 * 
	 * @param productsToBeReordered
	 */
	public void setProductsToBeReordered(StringBuilder productsToBeReordered) {
		this.productsToBeReordered = productsToBeReordered;
	}

	/**
	 * getter for whether a product has been reordered
	 * 
	 * @return isProductReordered
	 */
	public String getIsProductReordered() {
		return isProductReordered;
	}

	/**
	 * setter for whether a product is reordered
	 * 
	 * @param isProductReordered
	 */
	public void setIsProductReordered(String isProductReordered) {
		this.isProductReordered = isProductReordered;
	}

	/**
	 * getter for a checkout line item
	 * 
	 * @return checkoutLineItem
	 */
	public String getCheckoutLineItem() {
		return checkoutLineItem;
	}

	/**
	 * setter for a checkout line item
	 * 
	 * @param checkoutLineItem
	 */
	public void setCheckoutLineItem(String checkoutLineItem) {
		this.checkoutLineItem = checkoutLineItem;
	}

	/**
	 * getter for a cart's total price
	 * 
	 * @return cartTotalPrice
	 */
	public String getCartTotalPrice() {
		return cartTotalPrice;
	}

	/**
	 * setter for a cart's total price
	 * 
	 * @param cartTotalPrice
	 */
	public void setCartTotalPrice(String cartTotalPrice) {
		this.cartTotalPrice = cartTotalPrice;
	}

	/**
	 * getter for the quantity of a product in a cart
	 * 
	 * @return productCartQuantity
	 */
	public String getProductCartQuantity() {
		return productCartQuantity;
	}

	/**
	 * setter for the quantity of a product in a cart
	 * 
	 * @param productCartQuantity
	 */
	public void setProductCartQuantity(String productCartQuantity) {
		this.productCartQuantity = productCartQuantity;
	}

	/**
	 * Sets all String fields to "none"
	 */
	public void reset() {
		productId = "Invalid product id";
		productName = "No such product";
		productPrice = "No such product";
		productMinimumReorderLevel = "No such product";
		productQuantity = "No such product";
	}

}
