package com.cts.capstone.builder;

import com.cts.capstone.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBuilder {

	List<Customer> list;

	public CustomerBuilder() {
		list = new ArrayList<>();
	}

	public static Customer of() {
		return new Customer();
	}

	public CustomerBuilder w(Long id, String companyName, String contactName, String street, String city, String state) {
		list.add(of(id, companyName, contactName, street, city, state));
		return this;
	}

	public static Customer of(Long id, String companyName, String contactName, String street, String city, String state) {
		return new Customer(id, "subID", companyName, contactName, street, city, state);
	}

	public List<Customer> build() {
		return this.list;
	}
}
