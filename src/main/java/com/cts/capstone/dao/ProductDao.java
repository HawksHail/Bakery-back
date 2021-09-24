package com.cts.capstone.dao;

import com.cts.capstone.bean.Product;

import java.util.List;

public interface ProductDao {

	boolean createProduct(Product p);

	Product getProduct(long productId);

	List<Product> getAllProducts();

	List<Product> getAllProductsByCategoryId(long CategoryId);

	boolean updateProduct(Product p);

	boolean deleteProduct(long id);
}
