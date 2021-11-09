package com.cts.capstone.service;

import com.cts.capstone.model.Customer;
import com.cts.capstone.repository.CustomerRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}

	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@PostAuthorize("returnObject.sub == authentication.name or hasAuthority('view:customer')")
	public Customer findById(Long id) {
		return customerRepository.findById(id).orElse(null);
	}

	public Customer add(Customer customer) {
		return customerRepository.save(customer);
	}

	@Transactional
	public boolean delete(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			customerRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@PreAuthorize("#sub == authentication.name or hasAuthority('view:customer')")
	public Customer findBySub(String sub) {
		return customerRepository.findBySub(sub).orElse(null);
	}
}
