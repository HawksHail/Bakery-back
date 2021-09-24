package com.cts.capstone.repository;

import com.cts.capstone.bean.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
	Optional<Product> findByProductName(String name);
	List<Product> findByCategoryId(long id);
}
