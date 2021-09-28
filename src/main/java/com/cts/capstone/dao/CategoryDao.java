package com.cts.capstone.dao;

import com.cts.capstone.model.Category;

import java.util.List;

public interface CategoryDao {

	boolean createCategory(Category c);

	Category getCategory(long categoryId);

	List<Category> getAllCategories();

	boolean updateCategory(Category c);

	boolean deleteCategory(long id);
}
