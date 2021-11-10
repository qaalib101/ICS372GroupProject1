package edu.ics372.groupProject1.facade;

import edu.ics372.groupProject1.entities.Cart;
import edu.ics372.groupProject1.entities.Member;
import edu.ics372.groupProject1.entities.Order;
import edu.ics372.groupProject1.entities.Product;
import edu.ics372.groupProject1.entities.Transaction;

/*
 * The DataTransfer class is used to transfer data between the UserInterface and the GroceryStore class.
 * It also supports iteration for Member, Product and Order class by copying their fields 
 * and sending that information in either direction.
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
	private String transactionDate;
	private String transactionTotalPrice;
	private String productCartQuantity;
	private String cartTotalPrice;
	private String checkoutLineItem;
	private String isProductReordered;
	private StringBuilder productsToBeReordered;

	/*
	 * This sets all fields to "none"
	 */

	public DataTransfer() {
		reset();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberID) {
		this.memberId = memberID;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberDate() {
		return memberDate;
	}

	public void setMemberDate(String memberDate) {
		this.memberDate = memberDate;
	}

	public double getMemberFee() {
		return Double.valueOf(memberFee);
	}

	public void setMemberFee(String memberFee) {
		this.memberFee = memberFee;

	}

	/*
	 * Sets all member field using the member parameter
	 * 
	 * @param member whose field should be copied
	 */
	public void setMemberFields(Member member) {
		setMemberId(member.getId());
		setMemberName(member.getName());
		setMemberAddress(member.getAddress());
		setMemberPhone(member.getPhone());
		setMemberDate(member.getDate());
		setMemberFee(member.getFee());
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getProductPrice() {
		return Double.valueOf(productPrice);
	}

	public void setProductPrice(String productCurrentPrice) {
		this.productPrice = productCurrentPrice;
	}

	public int getProductMinimumReorderLevel() {
		return Integer.parseInt(productMinimumReorderLevel);
	}

	public void setProductMinimumReorderLevel(String productMinimumReorderLevel) {
		this.productMinimumReorderLevel = productMinimumReorderLevel;
	}

	public String getProductQuantity() {
		return productQuantity;
	}

	/*
	 * sets all product fields using the product parameter
	 * 
	 * @param product whose field should be copied
	 * 
	 */

	public void setProductFields(Product product) {
		setProductId(product.getId());
		setProductName(product.getName());
		setProductQuantity(product.getQuantity());
		setProductPrice(product.getPrice());
		setProductMinimumReorderLevel(product.getReorderLevel());
	}

	public String getOrderProductName() {
		return orderProductName;
	}

	public void setOrderProductName(String orderProductName) {
		this.orderProductName = orderProductName;
	}

	public String getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(String orderProductId) {
		this.orderProductId = orderProductId;
	}

	public String getAmountOrdered() {
		return amountOrdered;
	}

	public void setAmountOrdered(String orderAmountOrdered) {
		this.amountOrdered = orderAmountOrdered;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	/*
	 * sets all order fields using the order parameter
	 * 
	 * @param product whose field should be copied
	 * 
	 */

	public void setOrderFields(Order order) {
		orderProductId = order.getProductId();
		orderProductName = order.getProductName();
		amountOrdered = order.getAmount();
	}


	public String getTransactionTotalPrice() {
		return transactionTotalPrice;
	}

	public void setTransactionTotalPrice(String transactionTotalPrice) {
		this.transactionTotalPrice = transactionTotalPrice;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String calendar) {
		this.transactionDate = calendar;
	}

	/*
	 * sets all transaction fields using the transaction parameter
	 * 
	 * @param transaction whose field should be copied
	 * 
	 */

	public void setTransactionFields(Transaction transaction) {
		setTransactionDate(transaction.getTodaysDate());
		setTransactionTotalPrice(Double.toString(transaction.getTransactionTotal()));
	}

	public StringBuilder getProductsToBeReordered() {
		return productsToBeReordered;
	}

	public void setProductsToBeReordered(StringBuilder productsToBeReordered) {
		this.productsToBeReordered = productsToBeReordered;
	}

	public String getIsProductReordered() {
		return isProductReordered;
	}

	public void setIsProductReordered(String isProductReordered) {
		this.isProductReordered = isProductReordered;
	}

	public String getCheckoutLineItem() {
		return checkoutLineItem;
	}

	public void setCheckoutLineItem(String checkoutLineItem) {
		this.checkoutLineItem = checkoutLineItem;
	}

	public String getCartTotalPrice() {
		return cartTotalPrice;
	}

	public void setCartTotalPrice(String cartTotalPrice) {
		this.cartTotalPrice = cartTotalPrice;
	}

	public String getProductCartQuantity() {
		return productCartQuantity;
	}

	public void setProductCartQuantity(String productCartQuantity) {
		this.productCartQuantity = productCartQuantity;
	}

	/*
	 * This sets all fields to "Product fields"
	 */

	public StringBuilder getProductsToBeReordered() {
		return productsToBeReordered;
	}

	public void setProductsToBeReordered(StringBuilder productsToBeReordered) {
		this.productsToBeReordered = productsToBeReordered;
	}

	public String getIsProductReordered() {
		return isProductReordered;
	}

	public void setIsProductReordered(String isProductReordered) {
		this.isProductReordered = isProductReordered;
	}

	public String getCheckoutLineItem() {
		return checkoutLineItem;
	}

	public void setCheckoutLineItem(String checkoutLineItem) {
		this.checkoutLineItem = checkoutLineItem;
	}

	public String getCartTotalPrice() {
		return cartTotalPrice;
	}

	public void setCartTotalPrice(String cartTotalPrice) {
		this.cartTotalPrice = cartTotalPrice;
	}

	public String getProductCartQuantity() {
		return productCartQuantity;
	}

	public void setProductCartQuantity(String productCartQuantity) {
		this.productCartQuantity = productCartQuantity;
	}

	/*
	 * sets all productCart fields using the productCart parameter
	 * 
	 * @param productCart whose field should be copied
	 * 
	 */

	public void setProductFieldsFields(Cart cart) {
		//
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

