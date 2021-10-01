package com.cts.capstone.controller;

import com.cts.capstone.model.Customer;
import com.cts.capstone.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("customer")
public class CustomerController {

	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	public void setCustomerService(CustomerService categoryService) {
		this.customerService = categoryService;
	}

	@GetMapping()
	public List<Customer> getAllCustomers() {
		return customerService.findAll();
	}

	@GetMapping("{id}")
	public Customer getCustomer(@PathVariable String id) {
		return customerService.findById(id);
	}

	@PostMapping()
	public Customer addCustomer(@RequestBody Customer category) {
		return customerService.add(category);
	}
}
