package com.cts.capstone.controller;

import com.cts.capstone.exception.CustomerNotFoundException;
import com.cts.capstone.exception.ProductNotFoundException;
import com.cts.capstone.model.Customer;
import com.cts.capstone.model.Product;
import com.cts.capstone.service.CustomerService;
import com.cts.capstone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
@CrossOrigin(originPatterns = {"https://master.ditbstqq8z8x3.amplifyapp.com/", "http://localhost:*"})
public class CartController {

	@Autowired
	CustomerService customerService;

	@Autowired
	ProductService productService;

	public CartController(CustomerService customerService, ProductService productService) {
		this.customerService = customerService;
		this.productService = productService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("{customerId}")
	public ResponseEntity<Object> getCart(@PathVariable String customerId) {
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException(customerId);
		}
		return ResponseEntity.ok(customer.getCart());
	}

	@PostMapping("{customerId}/{productId}")
	public ResponseEntity<Object> addCartProduct(@PathVariable String customerId, @PathVariable Long productId) {
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException(customerId);
		}
		Product product = productService.findById(productId);
		if (product == null) {
			throw new ProductNotFoundException(productId);
		}

		customer.getCart().add(product);
		Customer add = customerService.add(customer);
		return ResponseEntity.ok(add.getCart());
	}

	@DeleteMapping("{customerId}/{productId}")
	public ResponseEntity<Object> deleteCartProduct(@PathVariable String customerId, @PathVariable Long productId) {
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException(customerId);
		}
		Product product = productService.findById(productId);
		if (product == null) {
			throw new ProductNotFoundException(productId);
		}

		boolean remove = customer.getCart().remove(product);
		Customer add = customerService.add(customer);
		return ResponseEntity.ok(add.getCart());
	}

	@DeleteMapping("{customerId}}")
	public ResponseEntity<Object> deleteCartAllProduct(@PathVariable String customerId) {
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException(customerId);
		}

		customer.getCart().clear();
		Customer add = customerService.add(customer);
		return ResponseEntity.noContent().build();
	}
}
