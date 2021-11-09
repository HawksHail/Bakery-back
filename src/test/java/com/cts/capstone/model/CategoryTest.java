package com.cts.capstone.model;

import com.cts.capstone.builder.CategoryBuilder;
import com.cts.capstone.builder.ProductBuilder;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
		category.setId(123L);
		assertEquals(123L, category.getId());
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

	@Test
	void setProductList() {
		List<Product> expected = new ProductBuilder()
				.w(1, "product1", "123")
				.w(2, "product2", "456")
				.build();
		category.setProductList(expected);
		assertEquals(expected, category.getProductList());
	}

	@Test
	void hashcodeAndEquals() {
		Category x = CategoryBuilder.of(1, "name", "desc");
		Category y = CategoryBuilder.of(1, "name", "desc");
		Category a = CategoryBuilder.of();
		Category b = CategoryBuilder.of();

		assertTrue(x.equals(y) && y.equals(x));
		assertEquals(x.hashCode(), y.hashCode());
		assertTrue(a.equals(b) && b.equals(a));
		assertEquals(a.hashCode(), b.hashCode());
	}

	@Test
	void toStringTest() {
		ToStringVerifier.forClass(Category.class)
				.withClassName(NameStyle.SIMPLE_NAME)
				.withIgnoredFields("productList")
				.verify();
	}

}