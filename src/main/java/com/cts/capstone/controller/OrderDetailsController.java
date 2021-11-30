package com.cts.capstone.controller;

import com.cts.capstone.exception.ExceptionResponse;
import com.cts.capstone.exception.OrderDetailsNotFoundException;
import com.cts.capstone.model.Order;
import com.cts.capstone.model.OrderDetails;
import com.cts.capstone.model.OrderDetailsKey;
import com.cts.capstone.model.Product;
import com.cts.capstone.service.OrderDetailsService;
import com.cts.capstone.service.OrderService;
import com.cts.capstone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("order/details")
public class OrderDetailsController {

	private OrderDetailsService orderDetailsService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	public OrderDetailsController(OrderDetailsService orderDetailsService) {
		super();
		this.orderDetailsService = orderDetailsService;
	}

	public OrderDetailsService getOrderDetailsService() {
		return orderDetailsService;
	}

	public void setOrderDetailsService(OrderDetailsService orderDetailsService) {
		this.orderDetailsService = orderDetailsService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping()
	@PreAuthorize("hasAuthority('view:order')")
	public List<OrderDetails> getAllOrderDetails() {
		return orderDetailsService.findAll();
	}

	@PostMapping()
	@PreAuthorize("isAuthenticated()") //does not test if user owns order
	public ResponseEntity<Object> addOrderDetailsList(@Valid @RequestBody List<OrderDetails> list) {
		if (list.size() < 1) {
			return ResponseEntity.badRequest().body(new ExceptionResponse(
					LocalDateTime.now(),
					HttpStatus.BAD_REQUEST,
					HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"Empty list",
					"order/details"));
		}
		try {
			for (OrderDetails od : list) {
				Order order = orderService.findById(od.getId().getOrderId());
				if (order == null) {
					return ResponseEntity.badRequest().body(new ExceptionResponse(
							LocalDateTime.now(),
							HttpStatus.BAD_REQUEST,
							HttpStatus.BAD_REQUEST.getReasonPhrase(),
							"Order " + od.getId().getOrderId() + " not found",
							"order/details"));
				}
				od.setOrder(order);

				Product product = productService.findById(od.getId().getProductId());
				if (product == null) {
					return ResponseEntity.badRequest().body(new ExceptionResponse(
							LocalDateTime.now(),
							HttpStatus.BAD_REQUEST,
							HttpStatus.BAD_REQUEST.getReasonPhrase(),
							"Product " + od.getId().getProductId() + " not found",
							"order/details"));
				}
				od.setProduct(product);
			}
		} catch (NullPointerException ex) {
			return ResponseEntity.badRequest().body(new ExceptionResponse(
					LocalDateTime.now(),
					HttpStatus.BAD_REQUEST,
					HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"ID was missing",
					"order/details"));
		}


		List<OrderDetails> added = orderDetailsService.addList(list);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(added.get(0).getId().getOrderId()).toUri();
		return ResponseEntity.created(location).body(added);
	}

	@GetMapping(value = "{orderId}/product/{productId}")
	@PreAuthorize("hasAuthority('view:order')")
	public OrderDetails getOrderDetailsProduct(@PathVariable Long orderId, @PathVariable Long productId) {
		OrderDetails find = orderDetailsService.findById(new OrderDetailsKey(orderId, productId));
		if (find == null) {
			throw new OrderDetailsNotFoundException(new OrderDetailsKey(orderId, productId));
		}
		return find;
	}

	@PutMapping
	@PreAuthorize("hasAuthority('update:order')")
	public ResponseEntity<OrderDetails> putOrderDetails(@Valid @RequestBody OrderDetails orderDetails) {
		OrderDetails added = orderDetailsService.add(orderDetails);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{orderId}/product/{productId}")
	@PreAuthorize("hasAuthority('delete:order')")
	public ResponseEntity<OrderDetails> deleteOrderDetails(@PathVariable Long orderId, @PathVariable Long productId) {
		OrderDetailsKey key = new OrderDetailsKey(orderId, productId);
		boolean delete = orderDetailsService.delete(key);
		if (delete) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
