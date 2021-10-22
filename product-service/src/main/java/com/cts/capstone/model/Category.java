package com.cts.capstone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@Column(name = "categoryid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "categoryname", nullable = false, length = 15)
	@Length(max = 15, message = "Category name max length of 15")
	private String categoryName;

	@Column(name = "description")
	@Length(max = 255, message = "Description max length of 255")
	private String description;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("category")
	private List<Product> productList;

	public Category() {
		//Empty
	}

	public Category(long id, String categoryName, String description) {
		this.id = id;
		this.categoryName = categoryName;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, categoryName, description, productList);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Category category = (Category) o;
		return id == category.id && Objects.equals(categoryName, category.categoryName) && Objects.equals(description, category.description) && Objects.equals(productList, category.productList);
	}

	@Override
	public String toString() {
		return "Category{" +
				"id=" + id +
				", categoryName='" + categoryName + '\'' +
				", description='" + description + '\'' +
				", productList=" + productList +
				'}';
	}
}
