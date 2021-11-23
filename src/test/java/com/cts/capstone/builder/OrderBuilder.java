package com.cts.capstone.builder;

import com.cts.capstone.model.Customer;
import com.cts.capstone.model.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {

	List<Order> list;

	public OrderBuilder() {
		list = new ArrayList<>();
	}

	public static Order of() {
		return new Order();
	}

	public static Order of(Customer customer) {
		Order order = new Order();
		order.setCustomer(customer);
		order.setOrderDate(LocalDate.of(2021, 1, 1));
		return order;
	}

	public OrderBuilder w(Long id, Customer customer) {
		list.add(of(id, customer));
		return this;
	}

	public static Order of(Long id, Customer customer) {
		return new Order(id, customer, LocalDate.of(2021, 1, 1));
	}

	public OrderBuilder w(long orderId, Customer customer, int year, int month, int day) {
		list.add(of(orderId, customer, year, month, day));
		return this;
	}

	public static Order of(long orderId, Customer customer, int year, int month, int day) {
		return new Order(orderId, customer, LocalDate.of(year, month, day));
	}

	public List<Order> build() {
		return this.list;
	}
}
