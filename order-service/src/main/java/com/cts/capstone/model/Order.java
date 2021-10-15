package com.cts.capstone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@Column(name = "orderid")
	private long id;

	@Column(name = "customerid", length = 5)
	@Length(max = 5, message = "Customer ID max length 40")
	private String customerId;

	@Column(name = "orderdate")
	private LocalDate orderDate;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("order")
	private List<OrderDetails> detailsList;

	public Order() {
		//Empty
	}

	public Order(long id, String customerId, LocalDate orderDate) {
		this.id = id;
		this.customerId = customerId;
		this.orderDate = orderDate;
	}

	public Order(long id, String customerId) {
		this.id = id;
		this.customerId = customerId;
	}

	public List<OrderDetails> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<OrderDetails> detailsList) {
		this.detailsList = detailsList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		return Objects.hash(id, customerId, orderDate);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return id == order.id && Objects.equals(customerId, order.customerId) && Objects.equals(orderDate, order.orderDate);
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", customerId='" + customerId + '\'' +
				", orderDate=" + orderDate +
				", detailsList=" + detailsList +
				'}';
	}
}
