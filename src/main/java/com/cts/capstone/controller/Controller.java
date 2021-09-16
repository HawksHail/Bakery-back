package com.cts.capstone.controller;

import com.cts.capstone.bean.*;
import com.cts.capstone.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

	@Autowired
	DbService service;

	//TODO resource not found error

	@GetMapping(value = "product", produces = "application/json")
	public List<Product> getProduct() {
		return service.getAllProducts();
	}

	@GetMapping(value = "product/{id}", produces = "application/json")
	public Product getProduct(@PathVariable Long id) {
		return service.getProduct(id);
	}

	@GetMapping(value = "category", produces = "application/json")
	public List<Category> getCategory() {
		return service.getAllCategories();
	}

	@GetMapping(value = "category/{id}", produces = "application/json")
	public Category getCategory(@PathVariable Long id) {
		return service.getCategory(id);
	}

	@GetMapping(value = "customer", produces = "application/json")
	public List<Customer> getCustomer() {
		return service.getAllCustomers();
	}

	@GetMapping(value = "customer/{id}", produces = "application/json")
	public Customer getCustomer(@PathVariable String id) {
		return service.getCustomer(id);
	}

	@GetMapping(value = "order", produces = "application/json")
	public List<Order> getOrder() {
		return service.getAllOrders();
	}

	@GetMapping(value = "order/{id}", produces = "application/json")
	public Order getOrder(@PathVariable Long id) {
		return service.getOrder(id);
	}

	@GetMapping(value = "orderdetails", produces = "application/json")
	public List<OrderDetails> getAllOrderDetails() {
		return service.getAllOrderDetails();
	}

	@GetMapping(value = "orderdetails/{id}", produces = "application/json")
	public OrderDetails getOrderDetails(@PathVariable Long id) {
		return service.getOrderDetails(id);
	}

	@GetMapping(value = "supplier", produces = "application/json")
	public List<Supplier> getAllSuppliers() {
		return service.getAllSuppliers();
	}

	@GetMapping(value = "supplier/{id}", produces = "application/json")
	public Supplier getSupplier(@PathVariable Long id) {
		return service.getSupplier(id);
	}
}
