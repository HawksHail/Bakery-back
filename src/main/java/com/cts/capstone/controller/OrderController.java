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
@CrossOrigin("*")
//@CrossOrigin(originPatterns = {"https://master.ditbstqq8z8x3.amplifyapp.com/", "http://localhost:*"})
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
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(added.getId()).toUri();
		return ResponseEntity.created(location).body(added);
	}

	@GetMapping("customer/{customerId}")
	public List<Order> getOrdersByCustomer(@PathVariable String customerId) {
		return orderService.findByCustomerId(customerId);
	}

	@PutMapping
	public ResponseEntity<Order> putOrder(@Valid @RequestBody Order order) {
		Order added = orderService.add(order);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping
	public ResponseEntity<Order> deleteOrder(@Valid @RequestBody Order order) {
		orderService.delete(order);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Order> deleteOrderById(@PathVariable Long id) {
		orderService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
