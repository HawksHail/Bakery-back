package com.cts.capstone.controller;

import com.cts.capstone.exception.CategoryNotFoundException;
import com.cts.capstone.model.Category;
import com.cts.capstone.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

	public CategoryService getCategoryService() {
		return categoryService;
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
	@PreAuthorize("hasAuthority('create:category')")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) {
		Category added = categoryService.add(category);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(added.getId()).toUri();
		return ResponseEntity.created(location).body(added);
	}

	@PutMapping
	@PreAuthorize("hasAuthority('update:category')")
	public ResponseEntity<Category> putCategory(@Valid @RequestBody Category category) {
		Category added = categoryService.add(category);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('delete:category')")
	public ResponseEntity<Category> deleteCategoryById(@PathVariable Long id) {
		boolean delete = categoryService.delete(id);
		if (delete) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
