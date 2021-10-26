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
//@CrossOrigin("*")
@CrossOrigin(originPatterns = {"https://master.ditbstqq8z8x3.amplifyapp.com/", "http://localhost"})
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
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(added.getId()).toUri();
		return ResponseEntity.created(location).body(added);
	}

	@PutMapping
	public ResponseEntity<Category> putCategory(@Valid @RequestBody Category category) {
		Category added = categoryService.add(category);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping
	public ResponseEntity<Category> deleteCategory(@Valid @RequestBody Category category) {
		categoryService.delete(category);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Category> deleteCategoryById(@PathVariable Long id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
