package com.cts.capstone.controller;

import com.cts.capstone.exception.OrderNotFoundException;
import com.cts.capstone.model.Order;
import com.cts.capstone.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("order")
public class OrderController {

	private OrderService orderService;

	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping()
	@PreAuthorize("hasAuthority('view:order')")
	public List<Order> getAllOrders() {
		return orderService.findAll();
	}

	@GetMapping("{id}")
	@PostAuthorize("returnObject.customer.sub == authentication.name or hasAuthority('view:order')")
	public Order getOrder(@PathVariable Long id) {
		Order find = orderService.findById(id);
		if (find == null) {
			throw new OrderNotFoundException(id);
		}
		return find;
	}

	@PostMapping()
	@PreAuthorize("isAuthenticated()") // does not test if user owns order
	public ResponseEntity<Order> addOrder(@Valid @RequestBody Order order) {
		Order added = orderService.add(order);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(added.getId()).toUri();
		return ResponseEntity.created(location).body(added);
	}

	@GetMapping("customer/{customerId}")
	@PostAuthorize("((returnObject.size > 0 && returnObject[0].customer.sub == authentication.name) or returnObject.size == 0) or hasAuthority('view:order')")
	public List<Order> getOrdersByCustomer(@PathVariable Long customerId) {
		return orderService.findByCustomerId(customerId);
	}

	@PutMapping
	@PreAuthorize("hasAuthority('update:order')")
	public ResponseEntity<Order> putOrder(@Valid @RequestBody Order order) {
		Order added = orderService.add(order);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('delete:order')")
	public ResponseEntity<Order> deleteOrderById(@PathVariable Long id) {
		boolean delete = orderService.delete(id);
		if (delete) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
