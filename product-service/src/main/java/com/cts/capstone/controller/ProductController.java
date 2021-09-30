package com.cts.capstone.controller;

import com.cts.capstone.model.Product;
import com.cts.capstone.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("product")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService categoryService) {
		this.productService = categoryService;
	}

	@GetMapping()
	public List<Product> getAllProducts() {
		return productService.findAll();
	}

	@GetMapping("{id}")
	public Product getProduct(@PathVariable Long id) {
		return productService.findById(id);
	}

	@PostMapping()
	public Product addProduct(@RequestBody Product category) {
		return productService.add(category);
	}

	@GetMapping("category/{categoryId}")
	public List<Product> getAllProductsInCategory(@PathVariable Long categoryId) {
		return productService.findAllByCategoryId(categoryId);
	}
}
