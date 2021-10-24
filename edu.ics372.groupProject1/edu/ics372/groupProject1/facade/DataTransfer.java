package edu.ics372.groupProject1.facade;

import edu.ics372.groupProject1.entities.Product;

public class DataTransfer {
	private String productId;
	private String productName;
	private String productCurrentPrice;
	private String productMinimumReorderLevel;
	private String productQuantity;

	public DataTransfer() {
		reset();
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

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public void setProductFields(Product product) {
		productId = product.getId();
		productName = product.getName();
		productQuantity = product.getQuantity();
		productCurrentPrice = product.getCurrentPrice();
		productMinimumReorderLevel = product.getMinimumReorderLevel();
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
