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
@CrossOrigin(origins = "http://localhost:3000/")
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
	public Customer getCustomer(@PathVariable String id) {
		Customer find = customerService.findById(id);
		if (find == null) {
			throw new CustomerNotFoundException(id);
		}
		return find;
	}

	@PostMapping()
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
		Customer added = customerService.add(customer);
		if (added == null) {
			throw new CustomerNotFoundException(customer.getCustomerId());
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(added.getCustomerId()).toUri();
		return ResponseEntity.created(location).body(added);
	}
}
