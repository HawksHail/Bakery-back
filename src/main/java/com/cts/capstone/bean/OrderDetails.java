package com.cts.capstone.bean;

public class OrderDetails {
	private long orderId;
	private long productId;
	private int quantity;

	public OrderDetails() {
	}

	public OrderDetails(long orderId, long productId, int quantity) {
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderDetails{" +
				"orderId=" + orderId +
				", productId=" + productId +
				", quantity=" + quantity +
				'}';
	}
}
