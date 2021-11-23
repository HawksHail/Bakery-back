package com.cts.capstone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderid")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "customerid")
	@JsonIncludeProperties("customerid")
	private Customer customer;

	@Column(name = "orderdate")
	private LocalDate orderDate;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("order")
	private List<OrderDetails> detailsList;

	public Order() {
		//Empty
	}

	public Order(Long id, Customer customer, LocalDate orderDate) {
		this.id = id;
		this.customer = customer;
		this.orderDate = orderDate;
	}

	public List<OrderDetails> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<OrderDetails> detailsList) {
		this.detailsList = detailsList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
		return Objects.hash(id, customer, orderDate);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return Objects.equals(id, order.id) && Objects.equals(customer, order.customer) && Objects.equals(orderDate, order.orderDate);
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", customer=" + customer +
				", orderDate=" + orderDate +
				", detailsList=" + detailsList +
				'}';
	}
}
