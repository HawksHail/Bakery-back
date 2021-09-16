package com.cts.capstone.dao;

import com.cts.capstone.bean.Category;

import java.util.List;

public interface CategoryDao {

	Category getCategory(long categoryId);

	List<Category> getAllCategories();
}
