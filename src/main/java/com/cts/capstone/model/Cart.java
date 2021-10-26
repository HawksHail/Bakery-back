package com.cts.capstone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
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

	@ElementCollection
	@CollectionTable(name = "cart_item_mapping",
			joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "id")})
	@MapKeyColumn(name = "product_id")
	@Column(name = "quantity")
	private Map<Long, Integer> items;

	public Cart() {
		//Empty
		items = new HashMap<>();
	}

	public Cart(Long id, Customer customer, Map<Long, Integer> items) {
		this.id = id;
		this.customer = customer;
		this.items = items;
	}

	public void add(Long product) {
		Integer count = items.getOrDefault(product, 0);
		set(product, count + 1);
	}

	public void set(Long product, int count) {
		if (count < 1) {
			items.remove(product);
		} else {
			items.put(product, count);
		}
	}

	public void remove(Long product) {
		Integer count = items.getOrDefault(product, 0);
		set(product, count - 1);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Map<Long, Integer> getItems() {
		return items;
	}

	public void setItems(Map<Long, Integer> items) {
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
				", customer=" + customer +
				", items=" + items +
				'}';
	}

	public void clear() {
		items.clear();
	}
}
