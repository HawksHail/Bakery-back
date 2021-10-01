package com.cts.capstone.service;

import com.cts.capstone.exception.CustomerNotFoundException;
import com.cts.capstone.model.Customer;
import com.cts.capstone.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {

	private CustomerRepository categoryRepository;

	public CustomerService(CustomerRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	public void setCustomerService(CustomerRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Customer> findAll() {
		return categoryRepository.findAll();
	}

	public Customer findById(String id) {
		return categoryRepository.findByCustomerId(id).orElseThrow(() -> new CustomerNotFoundException(id));
	}

	public Customer add(Customer category) {
		return categoryRepository.save(category);
	}
}
