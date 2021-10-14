package com.cts.capstone.controller;

import com.cts.capstone.exception.OrderNotFoundException;
import com.cts.capstone.model.Order;
import com.cts.capstone.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("order")
@CrossOrigin(origins = "http://localhost:3000/")
public class OrderController {

	private OrderService orderService;

	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping()
	public List<Order> getAllOrders() {
		return orderService.findAll();
	}

	@GetMapping("{id}")
	public Order getOrder(@PathVariable Long id) {
		Order find = orderService.findById(id);
		if (find == null) {
			throw new OrderNotFoundException(id);
		}
		return find;
	}

	@PostMapping()
	public ResponseEntity<Order> addOrder(@Valid @RequestBody Order order) {
		Order added = orderService.add(order);
		if (added == null) {
			throw new OrderNotFoundException(order.getOrderId());
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(added.getOrderId()).toUri();
		return ResponseEntity.created(location).body(added);
	}

	@GetMapping("customer/{customerId}")
	public List<Order> getOrdersByCustomer(@PathVariable String customerId) {
		return orderService.findByCustomerId(customerId);
	}
}
