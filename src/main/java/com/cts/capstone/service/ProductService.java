package com.cts.capstone.service;

import com.cts.capstone.model.Product;
import com.cts.capstone.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	public void setProductService(ProductRepository categoryRepository) {
		this.productRepository = categoryRepository;
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product findById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	public Product add(Product category) {
		return productRepository.save(category);
	}

	public boolean delete(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			productRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
