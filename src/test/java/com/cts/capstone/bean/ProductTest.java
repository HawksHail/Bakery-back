package com.cts.capstone.bean;

import com.cts.capstone.builder.ProductBuilder;
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
		product = ProductBuilder.of(123L, "name", 456L, 789L, "765");
	}

	@Test
	void setProductId() {
		product.setProductId(654L);
		assertEquals(654L, product.getProductId());
	}

	@Test
	void setProductName() {
		product.setProductName("abc");
		assertEquals("abc", product.getProductName());
	}

	@Test
	void setSupplierId() {
		product.setSupplierId(654L);
		assertEquals(654L, product.getSupplierId());
	}

	@Test
	void setCategoryId() {
		product.setCategoryId(654L);
		assertEquals(654L, product.getCategoryId());
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
	public void hashcodeAndEquals() {
		Product x = ProductBuilder.of(123L, "name", 456L, 789L, "765");
		Product y = ProductBuilder.of(123L, "name", 456L, 789L, "765");
		Product a = ProductBuilder.of();
		Product b = ProductBuilder.of();

		assertTrue(x.equals(y) && y.equals(x));
		assertEquals(x.hashCode(), y.hashCode());
		assertTrue(a.equals(b) && b.equals(a));
		assertEquals(a.hashCode(), b.hashCode());
	}

	@Test
	public void toStringTest() {
		ToStringVerifier.forClass(Product.class)
				.withClassName(NameStyle.SIMPLE_NAME)
				.verify();
	}
}