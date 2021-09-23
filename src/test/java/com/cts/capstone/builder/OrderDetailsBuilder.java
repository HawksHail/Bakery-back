package com.cts.capstone.builder;

import com.cts.capstone.bean.OrderDetails;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsBuilder {

	List<OrderDetails> list;

	public OrderDetailsBuilder() {
		list = new ArrayList<>();
	}

	public static OrderDetails of() {
		return new OrderDetails();
	}

	public OrderDetailsBuilder w(long orderId, long productId, int quantity) {
		list.add(of(orderId, productId, quantity));
		return this;
	}

	public static OrderDetails of(long orderId, long productId, int quantity) {
		return new OrderDetails(orderId, productId, quantity);
	}

	public List<OrderDetails> build() {
		return this.list;
	}
}
