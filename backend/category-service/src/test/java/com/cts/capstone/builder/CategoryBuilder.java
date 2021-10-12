package com.cts.capstone.builder;

import com.cts.capstone.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryBuilder {

	List<Category> list;

	public CategoryBuilder() {
		list = new ArrayList<>();
	}

	public static Category of() {
		return new Category();
	}

	public CategoryBuilder w(int id, String name, String description) {
		list.add(of(id, name, description));
		return this;
	}

	public static Category of(long id, String name, String description) {
		return new Category(id, name, description);
	}

	public List<Category> build() {
		return this.list;
	}
}
