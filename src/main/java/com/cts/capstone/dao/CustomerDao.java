package com.cts.capstone.dao;

import com.cts.capstone.model.Customer;

import java.util.List;

public interface CustomerDao {

	boolean createCustomer(Customer c);

	Customer getCustomer(String customerId);

	List<Customer> getAllCustomers();

	boolean updateCustomer(Customer c);

	boolean deleteCustomer(String id);
}
