package com.cts.capstone.builder;

import com.cts.capstone.model.Order;
import com.cts.capstone.model.OrderDetails;
import com.cts.capstone.model.OrderDetailsKey;
import com.cts.capstone.model.Product;

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
		return new OrderDetails(new OrderDetailsKey(orderId, productId), quantity);
	}

	public static OrderDetails of(Order order, Product product, int quantity) {
		return new OrderDetails(order, product, quantity);
	}

	public List<OrderDetails> build() {
		return this.list;
	}
}
