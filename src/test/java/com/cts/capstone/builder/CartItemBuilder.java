package com.cts.capstone.builder;

import com.cts.capstone.model.CartItem;
import com.cts.capstone.model.Customer;
import com.cts.capstone.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartItemBuilder {

	List<CartItem> list;
	Customer customer;

	public CartItemBuilder(Customer customer) {
		list = new ArrayList<>();
		this.customer = customer;
	}

	public static CartItem of() {
		return new CartItem();
	}

	public CartItemBuilder w(Product product, int quantity) {
		list.add(of(customer, product, quantity));
		return this;
	}

	public static CartItem of(Customer customer, Product product, int quantity) {
		return new CartItem(customer, product, quantity);
	}

	public List<CartItem> build() {
		return this.list;
	}
}
