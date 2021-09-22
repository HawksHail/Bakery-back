package com.cts.capstone.repository;

import com.cts.capstone.bean.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	Optional<Category> findByCategoryName(String name);
}
