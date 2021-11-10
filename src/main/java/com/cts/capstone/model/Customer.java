package com.cts.capstone.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@Column(name = "customerid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;

	@Column(unique = true, nullable = false)
	private String sub;

	@Column(name = "companyname", length = 40)
	@Length(max = 40, message = "Company name max length 40")
	private String companyName;

	@Column(name = "contactname", length = 30)
	@Length(max = 30, message = "Contact name max length 40")
	private String contactName;

	@Column(name = "street", length = 60)
	@Length(max = 60, message = "Street max length 40")
	private String street;

	@Column(name = "city", length = 15)
	@Length(max = 15, message = "City max length 40")
	private String city;

	@Column(name = "state", length = 15)
	@Length(max = 15, message = "State max length 40")
	private String state;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerid")
	private List<CartItem> cart;

	public Customer() {
		cart = new ArrayList<>();
	}

	public Customer(Long customerId, String sub, String companyName, String contactName, String street, String city, String state) {
		this.customerId = customerId;
		this.sub = sub;
		this.companyName = companyName;
		this.contactName = contactName;
		this.street = street;
		this.city = city;
		this.state = state;
		cart = new ArrayList<>();
	}

	public Customer(Long customerId, String sub, String companyName, String contactName, String street, String city, String state, List<CartItem> cart) {
		this.customerId = customerId;
		this.sub = sub;
		this.companyName = companyName;
		this.contactName = contactName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.cart = cart;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<CartItem> getCart() {
		return cart;
	}

	public void setCart(List<CartItem> cart) {
		this.cart = cart;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, sub, companyName, contactName, street, city, state, cart);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Customer customer = (Customer) o;
		return Objects.equals(customerId, customer.customerId) && Objects.equals(sub, customer.sub) && Objects.equals(companyName, customer.companyName) && Objects.equals(contactName, customer.contactName) && Objects.equals(street, customer.street) && Objects.equals(city, customer.city) && Objects.equals(state, customer.state) && Objects.equals(cart, customer.cart);
	}

	@Override
	public String toString() {
		return "Customer{" +
				"customerId=" + customerId +
				", sub='" + sub + '\'' +
				", companyName='" + companyName + '\'' +
				", contactName='" + contactName + '\'' +
				", street='" + street + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", cart=" + cart +
				'}';
	}
}
