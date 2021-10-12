package com.cts.capstone.controller;

import com.cts.capstone.exception.CategoryNotFoundException;
import com.cts.capstone.model.Category;
import com.cts.capstone.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
		Category find = categoryService.findById(id);
		if (find == null) {
			throw new CategoryNotFoundException(id);
		}
		return find;
	}

	@PostMapping()
	public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) {
		Category added = categoryService.add(category);
		if (added == null) {
			throw new CategoryNotFoundException(category.getCategoryId());
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(added.getCategoryId()).toUri();
		return ResponseEntity.created(location).body(added);
	}
}
