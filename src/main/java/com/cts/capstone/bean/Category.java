package com.cts.capstone.bean;

import java.util.Objects;

public class Category {
	private long categoryId;
	private String categoryName;
	private String description;

	public Category() {
	}

	public Category(long categoryId, String categoryName, String description) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.description = description;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryId, categoryName, description);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Category category = (Category) o;
		return categoryId == category.categoryId && Objects.equals(categoryName, category.categoryName) && Objects.equals(description, category.description);
	}

	@Override
	public String toString() {
		return "Categories{" +
				"categoryId=" + categoryId +
				", categoryName='" + categoryName + '\'' +
				", description='" + description + '\'' +
				'}';
	}

}
