package com.cts.capstone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cart {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	@JsonIgnore
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_customerid")
	@JsonIgnoreProperties("cart")
	@JsonIncludeProperties("customerId")
	private Customer customer;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("cart")
	private List<CartItem> items;

	public Cart() {
		//Empty
		items = new ArrayList<>();
	}

	public Cart(Long id, Customer customer, List<CartItem> items) {
		this.id = id;
		this.customer = customer;
		this.items = items;
	}

	public void add(Product p) {
		for (CartItem item : items) {
			if (item.getProduct().equals(p)) {
				item.add();
				return;
			}
		}
		items.add(new CartItem(this, p, 1));
	}

	public void remove(Product p) {
		for (CartItem item : items) {
			if (item.getProduct().equals(p)) {
				int q = item.remove();
				if (q < 1) {
					items.remove(item);
					return;
				}
			}
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, customer, items);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cart cart = (Cart) o;
		return Objects.equals(id, cart.id) && Objects.equals(customer, cart.customer) && Objects.equals(items, cart.items);
	}

	@Override
	public String toString() {
		return "Cart{" +
				"id=" + id +
				", customer=" + customer.getCustomerId() +
				", items=" + items +
				'}';
	}

	public void clear() {
		items.clear();
	}
}
