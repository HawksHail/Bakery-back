package com.cts.capstone.service;

import com.cts.capstone.model.Customer;
import com.cts.capstone.repository.CustomerRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


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

	public boolean delete(Customer customer) {
		try {
			customerRepository.delete(customer);
		} catch (IllegalArgumentException ex) {
			return false;
		}
		return true;
	}

	public boolean delete(String id) {
		try {
			customerRepository.deleteByCustomerId(id);
		} catch (IllegalArgumentException | EmptyResultDataAccessException ex) {
			return false;
		}
		return true;
	}
}
