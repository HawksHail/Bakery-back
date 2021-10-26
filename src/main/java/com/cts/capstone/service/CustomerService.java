package com.cts.capstone.service;

import com.cts.capstone.model.Customer;
import com.cts.capstone.repository.CartRepository;
import com.cts.capstone.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	private CartRepository cartRepository;

	public CustomerService(CustomerRepository customerRepository, CartRepository cartRepository) {
		super();
		this.customerRepository = customerRepository;
		this.cartRepository = cartRepository;
	}

	public void setCartRepository(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
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

	@Transactional
	public boolean delete(String id) {
		Optional<Customer> customer = customerRepository.findByCustomerId(id);
		if (customer.isPresent()) {
			cartRepository.delete(customer.get().getCart());
			customerRepository.deleteByCustomerId(id);
			return true;
		} else {
			return false;
		}
	}
}
