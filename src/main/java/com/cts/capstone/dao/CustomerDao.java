package com.cts.capstone.dao;

import com.cts.capstone.bean.Customer;

import java.util.List;

public interface CustomerDao {

	Customer getCustomer(String customerId);

	List<Customer> getAllCustomers();
}
