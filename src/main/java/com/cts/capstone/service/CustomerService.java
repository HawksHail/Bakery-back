package com.cts.capstone.service;

import com.cts.capstone.model.Customer;
import com.cts.capstone.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	public void setCustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Customer findById(String id) {
		return customerRepository.findByCustomerId(id).orElse(null);
	}

	public Customer add(Customer customer) {
		return customerRepository.save(customer);
	}


	public boolean delete(String id) {
		Optional<Customer> customer = customerRepository.findByCustomerId(id);
		if (customer.isPresent()) {
			customerRepository.deleteByCustomerId(id);
			return true;
		} else {
			return false;
		}
	}
}
