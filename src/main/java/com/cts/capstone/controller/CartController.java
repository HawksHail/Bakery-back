package com.cts.capstone.controller;

import com.cts.capstone.bean.Order;
import com.cts.capstone.bean.OrderDetails;
import com.cts.capstone.service.DbService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class CartController {

	private static final Gson gson = new Gson();

	@Autowired
	DbService service;

	public CartController() {
	}

	public CartController(DbService service) {
		this.service = service;
	}

	/**
	 * Get all customers
	 *
	 * @return Json of all orders
	 */
	@GetMapping(value = "order", produces = "application/json")
	public String getOrder() {
		return gson.toJson(service.getAllOrders());
	}

	/**
	 * Get order with specified ID
	 *
	 * @param id Order ID to be retrieved
	 * @return Json of specified order
	 */
	@GetMapping(value = "order/{id}", produces = "application/json")
	public String getOrder(@PathVariable Long id) {
		return gson.toJson(service.getOrder(id));
	}

	/**
	 * Get all order details
	 *
	 * @return Json of all order details
	 */
	@GetMapping(value = {"orderdetails", "orderDetails"}, produces = "application/json")
	public String getOrderDetails() {
		return gson.toJson(service.getAllOrderDetails());
	}

	/**
	 * Get order details for specified ID
	 *
	 * @param id Order details ID to retrieve
	 * @return Json of specified order detail
	 */
	@GetMapping(value = {"orderdetails/{id}", "orderDetails/{id}"}, produces = "application/json")
	public String getOrderDetails(@PathVariable Long id) {
		return gson.toJson(service.getOrderDetails(id));
	}

}
