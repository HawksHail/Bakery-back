package com.cts.capstone.bean;

import com.cts.capstone.builder.OrderBuilder;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderTest {

	Order order;

	@BeforeEach
	void setUp() {
		order = OrderBuilder.of(123L, "cm1234");
	}

	@Test
	void setOrderId() {
		order.setOrderId(567L);
		assertEquals(567L, order.getOrderId());
	}

	@Test
	void setCustomerId() {
		order.setCustomerId("abc");
		assertEquals("abc", order.getCustomerId());
	}

	@Test
	void setOrderDate_LocalDate() {
		order.setOrderDate(LocalDate.of(2022, 2, 22));
		assertEquals(LocalDate.of(2022, 2, 22), order.getOrderDate());
	}

	@Test
	void setOrderDate_year_month_day() {
		order.setOrderDate(2022, 2, 22);
		assertEquals(LocalDate.of(2022, 2, 22), order.getOrderDate());
	}

	@Test
	void hashcodeAndEquals() {
		Order x = OrderBuilder.of(123L, "cm1234");
		Order y = OrderBuilder.of(123L, "cm1234");
		Order a = OrderBuilder.of();
		Order b = OrderBuilder.of();

		assertTrue(x.equals(y) && y.equals(x));
		assertEquals(x.hashCode(), y.hashCode());
		assertTrue(a.equals(b) && b.equals(a));
		assertEquals(a.hashCode(), b.hashCode());
	}

	@Test
	void toStringTest() {
		ToStringVerifier.forClass(Order.class)
				.withClassName(NameStyle.SIMPLE_NAME)
				.verify();
	}
}