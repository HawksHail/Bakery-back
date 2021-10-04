package com.cts.capstone.controller;

import com.cts.capstone.exception.ProductNotFoundException;
import com.cts.capstone.model.Product;
import com.cts.capstone.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("product")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping()
	public List<Product> getAllProducts() {
		return productService.findAll();
	}

	@GetMapping("{id}")
	public Product getProduct(@PathVariable Long id) {
		Product find = productService.findById(id);
		if (find == null) {
			throw new ProductNotFoundException(id);
		}
		return find;
	}

	@PostMapping()
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product added = productService.add(product);
		if (added == null) {
			throw new ProductNotFoundException(product.getProductId());
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(added.getCategoryId()).toUri();
		return ResponseEntity.created(location).body(added);
	}

	@GetMapping("category/{categoryId}")
	public List<Product> getAllProductsInCategory(@PathVariable Long categoryId) {
		return productService.findAllByCategoryId(categoryId);
	}
}
