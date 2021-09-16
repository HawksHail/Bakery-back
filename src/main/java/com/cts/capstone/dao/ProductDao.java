package com.cts.capstone.dao;

import com.cts.capstone.bean.Product;

import java.util.List;

public interface ProductDao {

	Product getProduct(long productId);

	List<Product> getAllProducts();
}
