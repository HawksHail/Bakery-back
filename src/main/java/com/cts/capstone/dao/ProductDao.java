package com.cts.capstone.dao;

import com.cts.capstone.model.Product;

import java.util.List;

public interface ProductDao {

	boolean createProduct(Product p);

	Product getProduct(long productId);

	List<Product> getAllProducts();

	List<Product> getAllProductsByCategoryId(long categoryId);

	boolean updateProduct(Product p);

	boolean deleteProduct(long id);
}
