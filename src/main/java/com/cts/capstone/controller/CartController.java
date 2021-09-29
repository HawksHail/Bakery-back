package com.cts.capstone.controller;

import com.cts.capstone.model.Order;
import com.cts.capstone.model.OrderDetails;
import com.cts.capstone.service.DbService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	 * @return Json of all customers
	 */
	@GetMapping(value = "customer", produces = "application/json")
	public String getCustomer() {
		return gson.toJson(service.getAllCustomers());
	}

	/**
	 * Get customer with specified ID
	 *
	 * @param id Customer ID to retrieve
	 * @return Json of specified customer
	 */
	@GetMapping(value = "customer/{id}", produces = "application/json")
	public String getCustomer(@PathVariable String id) {
		return gson.toJson(service.getCustomer(id));
	}

	/**
	 * Get all orders
	 *
	 * @return Json of all orders
	 */
	@GetMapping(value = "order", produces = "application/json")
	public String getOrder() {
		return gson.toJson(service.getAllOrders());
	}

	/**
	 * Create an order
	 *
	 * @param order order to be added
	 * @return order if successful, else null
	 */
	@PostMapping(value = "order")
	public Order createOrder(@RequestBody Order order) {
		if (!service.createOrder(order)) {
			return null;
		}
		return order;
	}

	/**
	 * Update an order
	 *
	 * @param order order to be updated
	 * @return order if successful, else null
	 */
	@PatchMapping(value = "order")
	public Order updateOrder(@RequestBody Order order) {
		if (!service.updateOrder(order)) {
			return null;
		}
		return order;
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

	/**
	 * Create a order details
	 *
	 * @param details details to create
	 * @return details if successful, else null
	 */
	@PostMapping(value = {"orderdetails", "orderDetails"})
	public OrderDetails createOrderDetails(@RequestBody OrderDetails details) {
		if (!service.createOrderDetails(details)) {
			return null;
		}
		return details;
	}

	/**
	 * Update a order details
	 *
	 * @param details details to update
	 * @return details if successful, else null
	 */
	@PatchMapping(value = {"orderdetails", "orderDetails"})
	public OrderDetails updateOrderDetails(@RequestBody OrderDetails details) {
		if (!service.updateOrderDetails(details)) {
			return null;
		}
		return details;
	}

	/**
	 * Get all orders for a specific user
	 *
	 * @param userId user to get orders for
	 * @return list of orders from specified user
	 */
	@GetMapping(value = "order/user/{userId}", produces = "application/json")
	public String getUserOrders(@PathVariable String userId) {
		List<Order> list = service.getOrdersForCustomer(userId);
		return gson.toJson(list);
	}

	/**
	 * Get all orderDetails for a specified order
	 *
	 * @param orderId order to get details for
	 * @return list of orderDetails for specified order
	 */
	@GetMapping(value = {"orderdetails/order/{orderId}", "orderDetails/order/{orderId}"}, produces = "application/json")
	public String getOrderDetailsForOrder(@PathVariable Long orderId) {
		List<OrderDetails> list = service.getDetailsForOrder(orderId);
		return gson.toJson(list);
	}

}
