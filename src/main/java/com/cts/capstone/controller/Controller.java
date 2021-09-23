package com.cts.capstone.controller;

import com.cts.capstone.service.DbService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	private static final Gson gson = new Gson();
	@Autowired
//	@Qualifier("DbServiceDao")
	DbService service;

	public Controller() {
	}

	public Controller(DbService service) {
		this.service = service;
	}

	@GetMapping(value = "product", produces = "application/json")
	public String getProduct() {
		return gson.toJson(service.getAllProducts());
	}

	@GetMapping(value = "product/{id}", produces = "application/json")
	public String getProduct(@PathVariable Long id) {
		return gson.toJson(service.getProduct(id));
	}

	@GetMapping(value = "category", produces = "application/json")
	public String getCategory() {
		return gson.toJson(service.getAllCategories());
	}

	@GetMapping(value = "category/{id}", produces = "application/json")
	public String getCategory(@PathVariable Long id) {
		return gson.toJson(service.getCategory(id));
	}

	@GetMapping(value = "customer", produces = "application/json")
	public String getCustomer() {
		return gson.toJson(service.getAllCustomers());
	}

	@GetMapping(value = "customer/{id}", produces = "application/json")
	public String getCustomer(@PathVariable String id) {
		return gson.toJson(service.getCustomer(id));
	}

	@GetMapping(value = "order", produces = "application/json")
	public String getOrder() {
		return gson.toJson(service.getAllOrders());
	}

	@GetMapping(value = "order/{id}", produces = "application/json")
	public String getOrder(@PathVariable Long id) {
		return gson.toJson(service.getOrder(id));
	}

	@GetMapping(value = {"orderdetails", "orderDetails"}, produces = "application/json")
	public String getOrderDetails() {
		return gson.toJson(service.getAllOrderDetails());
	}

	@GetMapping(value = {"orderdetails/{id}", "orderDetails/{id}"}, produces = "application/json")
	public String getOrderDetails(@PathVariable Long id) {
		return gson.toJson(service.getOrderDetails(id));
	}

	@GetMapping(value = "supplier", produces = "application/json")
	public String getSupplier() {
		return gson.toJson(service.getAllSuppliers());
	}

	@GetMapping(value = "supplier/{id}", produces = "application/json")
	public String getSupplier(@PathVariable Long id) {
		return gson.toJson(service.getSupplier(id));
	}
}
