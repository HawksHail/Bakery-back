package com.cts.capstone.bean;

import com.cts.capstone.builder.CategoryBuilder;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoryTest {

	Category category;

	@BeforeEach
	void setUp() {
		category = CategoryBuilder.of(100, "Fruit", "All fruits");
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