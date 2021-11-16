package com.cts.capstone.exception;

import com.cts.capstone.model.OrderDetailsKey;

public class OrderDetailsNotFoundException extends RuntimeException {

	private final static String message = "Could not find order details";

	public OrderDetailsNotFoundException() {
		super(message);
	}

	public OrderDetailsNotFoundException(OrderDetailsKey id) {
		this(id, null);
	}

	public OrderDetailsNotFoundException(OrderDetailsKey id, Throwable cause) {
		super(message + " " + id.getOrderId() + " " + id.getProductId(), cause);
	}
}
