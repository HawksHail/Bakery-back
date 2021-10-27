package com.cts.capstone.model;

import com.cts.capstone.builder.CategoryBuilder;
import com.cts.capstone.builder.ProductBuilder;
import com.cts.capstone.builder.SupplierBuilder;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductTest {

	Product product;

	@BeforeEach
	void setUp() {
		product = ProductBuilder.of(123L, "name", new Supplier(), new Category(), "765");
	}

	@Test
	void setProductId() {
		product.setId(654L);
		assertEquals(654L, product.getId());
	}

	@Test
	void setProductName() {
		product.setProductName("abc");
		assertEquals("abc", product.getProductName());
	}

	@Test
	void setSupplier() {
		Supplier s = SupplierBuilder.of(654L, "company name", "contact name");
		product.setSupplier(s);
		assertEquals(s, product.getSupplier());
	}

	@Test
	void setCategory() {
		Category c = CategoryBuilder.of(654L, "category name", "description");
		product.setCategory(c);
		assertEquals(c, product.getCategory());
	}

	@Test
	void setUnitPrice_BigDecimal() {
		product.setUnitPrice(new BigDecimal("3.14"));
		assertEquals(0, product.getUnitPrice().compareTo(new BigDecimal("3.14")));
	}

	@Test
	void setUnitPrice_double() {
		product.setUnitPrice("3.14");
		assertEquals(0, product.getUnitPrice().compareTo(new BigDecimal("3.14")));
	}

	@Test
	void hashcodeAndEquals() {
		Supplier s = SupplierBuilder.of(456L, "company name", "contact name");
		Category c = CategoryBuilder.of(789L, "name", "description");
		Product x = ProductBuilder.of(123L, "name", s, c, "765");
		Product y = ProductBuilder.of(123L, "name", s, c, "765");
		Product a = ProductBuilder.of();
		Product b = ProductBuilder.of();

		assertTrue(x.equals(y) && y.equals(x));
		assertEquals(x.hashCode(), y.hashCode());
		assertTrue(a.equals(b) && b.equals(a));
		assertEquals(a.hashCode(), b.hashCode());
	}

	@Test
	void toStringTest() {
		ToStringVerifier.forClass(Product.class)
				.withClassName(NameStyle.SIMPLE_NAME)
				.withIgnoredFields("supplier", "category")
				.verify();
	}
}