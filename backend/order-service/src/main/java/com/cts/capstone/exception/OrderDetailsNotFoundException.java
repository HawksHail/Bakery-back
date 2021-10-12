package com.cts.capstone.exception;

public class OrderDetailsNotFoundException extends RuntimeException {

	private final static String message = "Could not find order details";

	public OrderDetailsNotFoundException() {
		super(message);
	}

	public OrderDetailsNotFoundException(long id) {
		this(id, null);
	}

	public OrderDetailsNotFoundException(long id, Throwable cause) {
		super(message + " " + id, cause);
	}

	public OrderDetailsNotFoundException(long orderId, long productId) {
		this(orderId, productId, null);
	}

	public OrderDetailsNotFoundException(long orderId, long productId, Throwable cause) {
		super(message + " with order " + orderId + " and product " + productId, cause);

	}
}
