package com.cts.capstone.controller;

import com.cts.capstone.exception.CustomerNotFoundException;
import com.cts.capstone.exception.ProductNotFoundException;
import com.cts.capstone.model.*;
import com.cts.capstone.service.CartItemService;
import com.cts.capstone.service.CustomerService;
import com.cts.capstone.service.OrderService;
import com.cts.capstone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired
	CustomerService customerService;

	@Autowired
	ProductService productService;

	@Autowired
	CartItemService cartItemService;

	@Autowired
	OrderService orderService;


	public CartController() {
		//Empty
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public CartItemService getCartItemService() {
		return cartItemService;
	}

	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("{customerId}")
	public ResponseEntity<List<CartItem>> getCart(@PathVariable Long customerId) {
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException(customerId);
		}

		return ResponseEntity.ok(customer.getCart());
	}

	@PostMapping("{customerId}/{productId}")
	public ResponseEntity<List<CartItem>> addCartProduct(@PathVariable Long customerId,
	                                                     @PathVariable Long productId,
	                                                     @RequestParam(required = false) Integer q) {
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException(customerId);
		}
		Product product = productService.findById(productId);
		if (product == null) {
			throw new ProductNotFoundException(productId);
		}
		cartItemService.add(customer, product, q != null ? q : 1);

		return ResponseEntity.ok(cartItemService.findAllByCustomerId(customerId));
	}

	@DeleteMapping("{customerId}/{productId}")
	public ResponseEntity<List<CartItem>> deleteCartProduct(@PathVariable Long customerId,
	                                                        @PathVariable Long productId,
	                                                        @RequestParam(required = false) Integer q) {
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException(customerId);
		}
		Product product = productService.findById(productId);
		if (product == null) {
			throw new ProductNotFoundException(productId);
		}
		cartItemService.remove(customer, product, q != null ? q : 1);

		return ResponseEntity.ok(cartItemService.findAllByCustomerId(customerId));
	}

	@DeleteMapping("{customerId}")
	public ResponseEntity<List<CartItem>> deleteCartAllProduct(@PathVariable Long customerId) {
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException(customerId);
		}

		cartItemService.clear(customerId);

		return ResponseEntity.noContent().build();
	}

	@PostMapping("{customerId}")
	public ResponseEntity<Object> checkoutCart(@PathVariable Long customerId) {
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException(customerId);
		}

		Order order = orderService.add(new Order(customer));
		List<CartItem> cart = cartItemService.findAllByCustomerId(customerId);

		List<OrderDetails> details =
				cart.stream()
						.map((x) -> new OrderDetails(order, x.getProduct(), x.getQuantity()))
						.collect(Collectors.toList());
		order.setDetailsList(details);

		Order orderSaved = orderService.add(order);
		cartItemService.clear(customerId);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/order/{id}").buildAndExpand(orderSaved.getId()).toUri();
		return ResponseEntity.created(location).body(orderSaved);
	}
}
