package com.cts.capstone.controller;

import com.cts.capstone.exception.CustomerNotFoundException;
import com.cts.capstone.exception.ProductNotFoundException;
import com.cts.capstone.model.CartItem;
import com.cts.capstone.model.Customer;
import com.cts.capstone.model.Product;
import com.cts.capstone.service.CartItemService;
import com.cts.capstone.service.CustomerService;
import com.cts.capstone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired
	CustomerService customerService;

	@Autowired
	ProductService productService;

	@Autowired
	CartItemService cartItemService;

	public CartController(CustomerService customerService, ProductService productService, CartItemService cartItemService) {
		this.customerService = customerService;
		this.productService = productService;
		this.cartItemService = cartItemService;
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
}
