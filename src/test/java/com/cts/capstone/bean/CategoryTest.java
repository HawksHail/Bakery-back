package com.cts.capstone.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

	Category category;

	@BeforeEach
	void setUp() {
		category = new Category(100, "Fruit", "All fruits");
	}

	@Test
	void setCategoryId() {
		category.setCategoryId(123L);
		assertEquals(123L, category.getCategoryId());
	}

	@Test
	void setCategoryName() {
		category.setCategoryName("Vegetables");
		assertEquals("Vegetables", category.getCategoryName());
	}

	@Test
	void setDescription() {
		category.setDescription("All vegetables");
		assertEquals("All vegetables", category.getDescription());
	}

}