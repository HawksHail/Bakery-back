package com.cts.capstone.controller;

import com.cts.capstone.exception.CustomerNotFoundException;
import com.cts.capstone.model.Customer;
import com.cts.capstone.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('view:customer')")
	public List<Customer> getAllCustomers() {
		List<Customer> all = customerService.findAll();
		all.forEach(x -> x.setCart(null));
		return all;
	}

	@GetMapping("{id}")
	public Customer getCustomer(@PathVariable Long id) {
		Customer find = customerService.findById(id);
		if (find == null) {
			throw new CustomerNotFoundException(id);
		}
		return find;
	}

	@PostMapping("sub")
	public Customer getCustomerBySub(@RequestBody String sub) {
		Customer find = customerService.findBySub(sub);
		if (find == null) {
			throw new CustomerNotFoundException();
		}
		return find;
	}

	@PostMapping
	@PreAuthorize("isAuthenticated() or hasAuthority('update:customer')")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
		Customer added = customerService.add(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(added.getCustomerId()).toUri();
		return ResponseEntity.created(location).body(added);
	}

	@PutMapping
	@PreAuthorize("#customer.sub == authentication.name or hasAuthority('update:customer')")
	public ResponseEntity<Customer> putCustomer(@Valid @RequestBody Customer customer) {
		Customer added = customerService.add(customer);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('delete:customer')")
	public ResponseEntity<Customer> deleteCustomerById(@PathVariable Long id) {
		boolean delete = customerService.delete(id);
		if (delete) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
