package com.cts.capstone.service;

import com.cts.capstone.exception.CategoryNotFoundException;
import com.cts.capstone.model.Category;
import com.cts.capstone.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findById(Long id) {
		return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
	}

	public Category add(Category category) {
		return categoryRepository.save(category);
	}
}
