package com.cts.capstone.controller;

import com.cts.capstone.exception.CustomerNotFoundException;
import com.cts.capstone.exception.ProductNotFoundException;
import com.cts.capstone.model.CartItem;
import com.cts.capstone.model.Customer;
import com.cts.capstone.model.Product;
import com.cts.capstone.repository.CartItemRepository;
import com.cts.capstone.service.CustomerService;
import com.cts.capstone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired
	CustomerService customerService;

	@Autowired
	ProductService productService;

	@Autowired
	CartItemRepository cartRepository;

	public CartController(CustomerService customerService, ProductService productService, CartItemRepository cartRepository) {
		this.customerService = customerService;
		this.productService = productService;
		this.cartRepository = cartRepository;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
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
	public ResponseEntity<List<CartItem>> addCartProduct(@PathVariable Long customerId, @PathVariable Long productId) {
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException(customerId);
		}
		Product product = productService.findById(productId);
		if (product == null) {
			throw new ProductNotFoundException(productId);
		}
		Optional<CartItem> item = cartRepository.findByCustomerCustomerIdAndProductId(customerId, productId);
		if (item.isPresent()) {
			item.get().add();
			cartRepository.save(item.get());
		} else {
			cartRepository.save(new CartItem(customer, product, 1));
		}

		return ResponseEntity.ok(cartRepository.findAllByCustomerCustomerId(customerId));
	}

	@DeleteMapping("{customerId}/{productId}")
	public ResponseEntity<List<CartItem>> deleteCartProduct(@PathVariable Long customerId, @PathVariable Long productId) {
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException(customerId);
		}
		Product product = productService.findById(productId);
		if (product == null) {
			throw new ProductNotFoundException(productId);
		}
		Optional<CartItem> item = cartRepository.findByCustomerCustomerIdAndProductId(customerId, productId);
		if (item.isPresent()) {
			int quantity = item.get().remove();
			if (quantity < 1) {
				cartRepository.delete(item.get());
			} else {
				cartRepository.save(item.get());
			}
		}


		return ResponseEntity.ok(cartRepository.findAllByCustomerCustomerId(customerId));
	}

	@DeleteMapping("{customerId}")
	public ResponseEntity<List<CartItem>> deleteCartAllProduct(@PathVariable Long customerId) {
		Customer customer = customerService.findById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException(customerId);
		}

		cartRepository.deleteAllInBatch(cartRepository.findAllByCustomerCustomerId(customerId));

		return ResponseEntity.noContent().build();
	}
}
