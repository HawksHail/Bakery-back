package com.cts.capstone.bean;

import java.time.LocalDate;
import java.util.Objects;

public class Order {
	private long orderId;
	private String customerId;
	private LocalDate orderDate;

	public Order() {
	}

	public Order(long orderId, String customerId, LocalDate orderDate) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderDate = orderDate;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, customerId, orderDate);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return orderId == order.orderId && Objects.equals(customerId, order.customerId) && Objects.equals(orderDate, order.orderDate);
	}

	@Override
	public String toString() {
		return "Orders{" +
				"order_id=" + orderId +
				", customer_id='" + customerId + '\'' +
				", orderdate=" + orderDate +
				'}';
	}

	public void setOrderDate(int year, int month, int day) {
		this.orderDate = LocalDate.of(year, month, day);
	}
}
