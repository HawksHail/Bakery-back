package com.cts.capstone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CartItem {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	@JsonIgnore
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id")
	@JsonIgnoreProperties("items")
	private Cart cart;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Column
	private int quantity;

	public CartItem() {
		//Empty
	}

	public CartItem(Cart cart, Product product, int quantity) {
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
	}

	public CartItem(Product product) {
		this.product = product;
		this.quantity = 1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, cart, product, quantity);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CartItem cartItem = (CartItem) o;
		return quantity == cartItem.quantity && Objects.equals(id, cartItem.id) && Objects.equals(cart, cartItem.cart) && Objects.equals(product, cartItem.product);
	}

	@Override
	public String toString() {
		return "CartItem{" +
				"id=" + id +
				", cart=" + cart.getId() +
				", product=" + product.getId() +
				", quantity=" + quantity +
				'}';
	}

	public int add() {
		return ++quantity;
	}

	public int remove() {
		return --quantity;
	}

}
