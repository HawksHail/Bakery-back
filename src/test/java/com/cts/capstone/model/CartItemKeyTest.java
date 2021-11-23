package com.cts.capstone.model;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CartItemKeyTest {

	CartItemKey key;

	@BeforeEach
	void setUp() {
		key = new CartItemKey();
	}

	@Test
	void setProductId() {
		key.setProductId(123L);
		assertEquals(123L, key.getProductId());
	}

	@Test
	void setCustomerId() {
		key.setCustomerId(123L);
		assertEquals(123L, key.getCustomerId());
	}

	@Test
	void hashcodeAndEquals() {
		CartItemKey x = new CartItemKey(1L, 2L);
		CartItemKey y = new CartItemKey(1L, 2L);
		CartItemKey a = new CartItemKey();
		CartItemKey b = new CartItemKey();

		assertTrue(x.equals(y) && y.equals(x));
		assertEquals(x.hashCode(), y.hashCode());
		assertTrue(a.equals(b) && b.equals(a));
		assertEquals(a.hashCode(), b.hashCode());
	}

	@Test
	void toStringTest() {
		ToStringVerifier.forClass(CartItemKey.class)
				.withClassName(NameStyle.SIMPLE_NAME)
				.verify();
	}
}