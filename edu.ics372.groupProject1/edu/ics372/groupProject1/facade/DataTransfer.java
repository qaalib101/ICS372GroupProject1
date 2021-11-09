package edu.ics372.groupProject1.facade;

import edu.ics372.groupProject1.entities.Member;
import edu.ics372.groupProject1.entities.Order;
import edu.ics372.groupProject1.entities.Product;
import edu.ics372.groupProject1.entities.Transaction;

/*
 * The DataTransfer class is used to transfer data between the UserInterface and the GroceryStore class.
 * It also supports iteration for Member, Product and Order class by copying their fields 
 * and sending that information in either direction.
 * 
 * @author Leng Vang
 */

public class DataTransfer {
	private String memberId;
	private String memberName;
	private String memberAddress;
	private String memberPhone;
	private String memberFee;
	private String productId;
	private String productName;
	private String productCurrentPrice;
	private String productMinimumReorderLevel;
	private String productQuantity;
	private String orderProductName;
	private String orderProductId;
	private String amountOrdered;
	private String transactionDate;
	private String transactionTotalPrice;

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

	public String getMemberFee() {
		return memberPhone;
	}

	public void setMemberFee(double memberFee) {
		this.memberFee = Double.toString(memberFee);
	}

	/*
	 * Sets all member field using the member parameter
	 * 
	 * @param member whose field should be copied
	 */
	public void setMemberFields(Member member) {
		memberId = member.getId();
		memberName = member.getName();
		memberAddress = member.getAddress();
		memberPhone = member.getPhone();
		memberFee = member.getFee();
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

	public String getProductCurrentPrice() {
		return productCurrentPrice;
	}

	public void setProductCurrentPrice(String productCurrentPrice) {
		this.productCurrentPrice = productCurrentPrice;
	}

	public String getProductMinimumReorderLevel() {
		return productMinimumReorderLevel;
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
		productId = product.getId();
		productName = product.getName();
		productQuantity = product.getQuantity();
		productCurrentPrice = product.getPrice();
		productMinimumReorderLevel = product.getReorderLevel();
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

	public void setOrderField(Order order) {
		orderProductId = order.getProductId();
		orderProductName = order.getProductName();
		amountOrdered = order.getAmountOrdered();
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
		setTransactionDate(transaction.getDate());
		setTransactionTotalPrice(transaction.getTotalPrice());
	}

	/**
	 * Sets all String fields to "none"
	 */
	public void reset() {
		productId = "Invalid product id";
		productName = "No such product";
		productCurrentPrice = "No such product";
		productMinimumReorderLevel = "No such product";
		productQuantity = "No such product";
	}

}
