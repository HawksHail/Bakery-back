package com.cts.capstone.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@Column(name = "customerid", length = 5)
	private String customerId;

	@Column(name = "companyname", nullable = false, length = 40)
	private String companyName;

	@Column(name = "contactname", length = 30)
	private String contactName;

	@Column(name = "street", length = 60)
	private String street;

	@Column(name = "city", length = 15)
	private String city;

	@Column(name = "state", length = 15)
	private String state;

	public Customer() {
	}

	public Customer(String customerId, String companyName, String contactName, String street, String city, String state) {
		this.customerId = customerId;
		this.companyName = companyName;
		this.contactName = contactName;
		this.street = street;
		this.city = city;
		this.state = state;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCompanyName() {
		return companyName;
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

	@Override
	public int hashCode() {
		return Objects.hash(customerId, companyName, contactName, street, city, state);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Customer customer = (Customer) o;
		return Objects.equals(customerId, customer.customerId) && Objects.equals(companyName, customer.companyName) && Objects.equals(contactName, customer.contactName) && Objects.equals(street, customer.street) && Objects.equals(city, customer.city) && Objects.equals(state, customer.state);
	}

	@Override
	public String toString() {
		return "Customer{" +
				"customerId='" + customerId + '\'' +
				", companyName='" + companyName + '\'' +
				", contactName='" + contactName + '\'' +
				", street='" + street + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				'}';
	}
}
