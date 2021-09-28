package com.cts.capstone.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@Column(name = "orderid")
	private long orderId;

	@Column(name = "customerid", length = 5)
	private String customerId;

	@Column(name = "orderdate")
	private LocalDate orderDate;

	public Order() {
		//Empty
	}

	public Order(long orderId, String customerId, LocalDate orderDate) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderDate = orderDate;
	}

	public Order(long orderId, String customerId) {
		this.orderId = orderId;
		this.customerId = customerId;
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

	public void setOrderDate(int year, int month, int day) {
		this.orderDate = LocalDate.of(year, month, day);
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
		return "Order{" +
				"orderId=" + orderId +
				", customerId='" + customerId + '\'' +
				", orderDate=" + orderDate +
				'}';
	}
}
