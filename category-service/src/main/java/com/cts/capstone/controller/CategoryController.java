package com.cts.capstone.controller;

import com.cts.capstone.model.Category;
import com.cts.capstone.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("category")
public class CategoryController {

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping()
	public List<Category> getAllCategories() {
		return categoryService.findAll();
	}

	@GetMapping("{id}")
	public Category getCategory(@PathVariable Long id) {
		return categoryService.findById(id);
	}

	@PostMapping()
	public Category addCategory(@RequestBody Category category) {
		return categoryService.add(category);
	}
}
