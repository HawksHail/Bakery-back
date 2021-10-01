package com.cts.capstone.controller;

import com.cts.capstone.model.OrderDetails;
import com.cts.capstone.service.OrderDetailsService;
import org.springframework.web.bind.annotation.*;

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
	public List<OrderDetails> addOrderDetailsList(@RequestBody List<OrderDetails> list) {
		return orderDetailsService.addList(list);
	}

	@GetMapping(value = "{orderId}/product/{productId}")
	public OrderDetails getOrderDetailsProduct(@PathVariable Long orderId, @PathVariable Long productId) {
		return orderDetailsService.findByOrderIdAndProductId(orderId, productId);
	}
}
