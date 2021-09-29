package com.cts.capstone.controller;

import com.cts.capstone.service.DbService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	private static final Gson gson = new Gson();

	@Autowired
	DbService service;

	public ProductController() {
		//Empty
	}

	public ProductController(DbService service) {
		this.service = service;
	}

	/**
	 * Get all products
	 *
	 * @return Json of all products
	 */
	@GetMapping(value = "product", produces = "application/json")
	public String getProduct() {
		return gson.toJson(service.getAllProducts());
	}

	/**
	 * Get a product with an ID
	 *
	 * @param id ProductId to retrieve
	 * @return Json of product with specified ID
	 */
	@GetMapping(value = "product/{id}", produces = "application/json")
	public String getProduct(@PathVariable Long id) {
		return gson.toJson(service.getProduct(id));
	}

	/**
	 * Get all products in a specific category
	 *
	 * @param id CategoryID to get products from
	 * @return Json of all products in specified category
	 */
	@GetMapping(value = {"product/category/{id}", "category/product/{id}"}, produces = "application/json")
	public String getProductByCategory(@PathVariable Long id) {
		return gson.toJson(service.getProductsByCategoryId(id));
	}

	/**
	 * Get all categories
	 *
	 * @return Json of all categories
	 */
	@GetMapping(value = "category", produces = "application/json")
	public String getCategory() {
		return gson.toJson(service.getAllCategories());
	}

	/**
	 * Get category with specified ID
	 *
	 * @param id Category ID to retrieve
	 * @return Json of specified category
	 */
	@GetMapping(value = "category/{id}", produces = "application/json")
	public String getCategory(@PathVariable Long id) {
		return gson.toJson(service.getCategory(id));
	}

	/**
	 * Get all suppliers
	 *
	 * @return Json of all suppliers
	 */
	@GetMapping(value = "supplier", produces = "application/json")
	public String getSupplier() {
		return gson.toJson(service.getAllSuppliers());
	}

	/**
	 * Get supplier with specified ID
	 *
	 * @param id Supplier ID to retrieve
	 * @return Json of specified supplier
	 */
	@GetMapping(value = "supplier/{id}", produces = "application/json")
	public String getSupplier(@PathVariable Long id) {
		return gson.toJson(service.getSupplier(id));
	}
}
