package com.cts.capstone.controller;

import com.cts.capstone.exception.CustomerNotFoundException;
import com.cts.capstone.model.Customer;
import com.cts.capstone.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("customer")
public class CustomerController {

	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping()
	public List<Customer> getAllCustomers() {
		return customerService.findAll();
	}

	@GetMapping("{id}")
	public Customer getCustomer(@PathVariable Long id) {
		Customer find = customerService.findById(id);
		if (find == null) {
			throw new CustomerNotFoundException(id);
		}
		return find;
	}

	@PostMapping()
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
		Customer added = customerService.add(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(added.getCustomerId()).toUri();
		return ResponseEntity.created(location).body(added);
	}

	@PutMapping
	public ResponseEntity<Customer> putCustomer(@Valid @RequestBody Customer customer) {
		Customer added = customerService.add(customer);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Customer> deleteCustomerById(@PathVariable Long id) {
		boolean delete = customerService.delete(id);
		if (delete) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
