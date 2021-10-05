package com.cts.capstone.controller;

import com.cts.capstone.exception.OrderDetailsNotFoundException;
import com.cts.capstone.model.OrderDetails;
import com.cts.capstone.service.OrderDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("order/details")
public class OrderDetailsController {

	private OrderDetailsService orderDetailsService;

	public OrderDetailsController(OrderDetailsService orderDetailsService) {
		super();
		this.orderDetailsService = orderDetailsService;
	}

	public void setOrderDetailsService(OrderDetailsService orderDetailsService) {
		this.orderDetailsService = orderDetailsService;
	}

	@GetMapping()
	public List<OrderDetails> getAllOrderDetails() {
		return orderDetailsService.findAll();
	}

	@GetMapping("{id}")
	public List<OrderDetails> getAllOrderDetailsForOrder(@PathVariable Long id) {
		return orderDetailsService.findAllById(id);
	}

	@PostMapping()
	public ResponseEntity<List<OrderDetails>> addOrderDetailsList(@Valid @RequestBody List<OrderDetails> list) {
		List<OrderDetails> added = orderDetailsService.addList(list);
		if (added == null) {
			throw new OrderDetailsNotFoundException();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(added.get(0).getOrderId()).toUri();
		return ResponseEntity.created(location).body(added);
	}

	@GetMapping(value = "{orderId}/product/{productId}")
	public OrderDetails getOrderDetailsProduct(@PathVariable Long orderId, @PathVariable Long productId) {
		OrderDetails find = orderDetailsService.findByOrderIdAndProductId(orderId, productId);
		if (find == null) {
			throw new OrderDetailsNotFoundException(orderId, productId);
		}
		return find;
	}
}
