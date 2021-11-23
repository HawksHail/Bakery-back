package com.cts.capstone.model;

import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.builder.OrderBuilder;
import com.cts.capstone.builder.OrderDetailsBuilder;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderDetailsTest {

	OrderDetails orderDetails;

	@BeforeEach
	void setUp() {
		orderDetails = OrderDetailsBuilder.of(123L, 456L, 100);
	}

	@Test
	void setId() {
		orderDetails.setId(new OrderDetailsKey(987L, 123L));
		assertEquals(new OrderDetailsKey(987L, 123L), orderDetails.getId());
	}

	@Test
	void setOrderId() {
		orderDetails.getId().setOrderId(987L);
		assertEquals(987L, orderDetails.getId().getOrderId());
	}

	@Test
	void setProductId() {
		orderDetails.getId().setProductId(123L);
		assertEquals(123L, orderDetails.getId().getProductId());
	}

	@Test
	void setOrder() {
		Order expected = OrderBuilder.of(123L, CustomerBuilder.of(1234L));
		orderDetails.setOrder(expected);
		assertEquals(expected, orderDetails.getOrder());
	}

	@Test
	void setQuantity() {
		orderDetails.setQuantity(987);
		assertEquals(987, orderDetails.getQuantity());
	}

	@Test
	void hashcodeAndEquals() {
		OrderDetails x = OrderDetailsBuilder.of(123L, 456L, 100);
		OrderDetails y = OrderDetailsBuilder.of(123L, 456L, 100);
		OrderDetails a = OrderDetailsBuilder.of();
		OrderDetails b = OrderDetailsBuilder.of();

		assertTrue(x.equals(y) && y.equals(x));
		assertEquals(x.hashCode(), y.hashCode());
		assertTrue(a.equals(b) && b.equals(a));
		assertEquals(a.hashCode(), b.hashCode());
	}

	@Test
	void toStringTest() {
		ToStringVerifier.forClass(OrderDetails.class)
				.withClassName(NameStyle.SIMPLE_NAME)
				.verify();
	}
}