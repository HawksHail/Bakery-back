package com.cts.capstone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orderdetails")
public class OrderDetails {

	@EmbeddedId
	private OrderDetailsKey id;

	@ManyToOne
	@MapsId("orderId")
	@JoinColumn(name = "orderid")
	@JsonIgnoreProperties("detailsList")
	private Order order;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "productid")
	private Product product;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	public OrderDetails() {
		id = new OrderDetailsKey();
	}

	public OrderDetails(OrderDetailsKey id, int quantity) {
		this.id = id;
		this.quantity = quantity;
	}

	public OrderDetails(Order order, Product product, int quantity) {
		this.id = new OrderDetailsKey(order.getId(), product.getId());
		this.order = order;
		this.product = product;
		this.quantity = quantity;
	}

	public OrderDetailsKey getId() {
		return id;
	}

	public void setId(OrderDetailsKey id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, order, quantity);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderDetails that = (OrderDetails) o;
		return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(order, that.order);
	}

	@Override
	public String toString() {
		return "OrderDetails{" +
				"id=" + id +
				", order=" + order +
				", product=" + product +
				", quantity=" + quantity +
				'}';
	}
}
