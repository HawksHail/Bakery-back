package com.cts.capstone.service;

import com.cts.capstone.model.Category;
import com.cts.capstone.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	public void setCategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> findAll() {
		return categoryRepository.findAllByOrderByCategoryNameAsc();
	}

	public Category findById(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	public Category add(Category category) {
		return categoryRepository.save(category);
	}

	public boolean delete(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			categoryRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
