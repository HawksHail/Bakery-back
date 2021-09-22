package com.cts.capstone.controller;

import com.cts.capstone.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
//	@Qualifier("DbServiceDao")
	DbService service;

	//TODO resource not found error

	@GetMapping(value = "product", produces = "application/json")
	public String getProduct() {
		return service.getAllProducts().toString();
	}

	@GetMapping(value = "product/{id}", produces = "application/json")
	public String getProduct(@PathVariable Long id) {
		return service.getProduct(id).toString();
	}

	@GetMapping(value = "category", produces = "application/json")
	public String getCategory() {
		return service.getAllCategories().toString();
	}

	@GetMapping(value = "category/{id}", produces = "application/json")
	public String getCategory(@PathVariable Long id) {
		return service.getCategory(id).toString();
	}

	@GetMapping(value = "customer", produces = "application/json")
	public String getCustomer() {
		return service.getAllCustomers().toString();
	}

	@GetMapping(value = "customer/{id}", produces = "application/json")
	public String getCustomer(@PathVariable String id) {
		return service.getCustomer(id).toString();
	}

	@GetMapping(value = "order", produces = "application/json")
	public String getOrder() {
		return service.getAllOrders().toString();
	}

	@GetMapping(value = "order/{id}", produces = "application/json")
	public String getOrder(@PathVariable Long id) {
		return service.getOrder(id).toString();
	}

	@GetMapping(value = {"orderdetails", "orderDetails"}, produces = "application/json")
	public String getAllOrderDetails() {
		return service.getAllOrderDetails().toString();
	}

	@GetMapping(value = {"orderdetails/{id}", "orderDetails/{id}"}, produces = "application/json")
	public String getOrderDetails(@PathVariable Long id) {
		return service.getOrderDetails(id).toString();
	}

	@GetMapping(value = "supplier", produces = "application/json")
	public String getAllSuppliers() {
		return service.getAllSuppliers().toString();
	}

	@GetMapping(value = "supplier/{id}", produces = "application/json")
	public String getSupplier(@PathVariable Long id) {
		return service.getSupplier(id).toString();
	}
}
