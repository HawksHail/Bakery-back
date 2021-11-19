package com.cts.capstone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CartItem {

	@Id
	@Column(name = "id")
	@JsonIgnore
	@GeneratedValue
	private Long cartItemId;

	@ManyToOne
	@JoinColumn(name = "customerid")
	@JsonIgnore
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "productid")
	private Product product;

	@Column
	private int quantity;

	public CartItem() {
		//Empty
	}

	public CartItem(Product product) {
		this.product = product;
		this.quantity = 1;
	}

	public CartItem(Customer customer, Product product, int quantity) {
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
	}

	public Long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int add() {
		return add(1);
	}

	public int add(int amount) {
		return quantity += amount;
	}

	public int remove() {
		return remove(1);
	}

	public int remove(int amount) {
		return quantity -= amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartItemId, customer, product, quantity);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CartItem cartItem = (CartItem) o;
		return quantity == cartItem.quantity && Objects.equals(cartItemId, cartItem.cartItemId) && Objects.equals(customer, cartItem.customer) && Objects.equals(product, cartItem.product);
	}

	@Override
	public String toString() {
		return "CartItem{" +
				"cartItemId=" + cartItemId +
				", customer=" + (customer != null ? customer.getCustomerId() : null) +
				", product=" + (product != null ? product.getId() : null) +
				", quantity=" + quantity +
				'}';
	}
}
