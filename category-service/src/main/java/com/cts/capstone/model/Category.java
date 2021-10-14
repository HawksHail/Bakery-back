package com.cts.capstone.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@Column(name = "categoryid")
	private long categoryId;

	@Column(name = "categoryname", nullable = false, length = 15)
	@Length(max = 15, message = "Category name max length of 15")
	private String categoryName;

	@Column(name = "description")
	@Length(max = 255, message = "Description max length of 255")
	private String description;

	public Category() {
		//Empty
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
		return "Category{" +
				"categoryId=" + categoryId +
				", categoryName='" + categoryName + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
