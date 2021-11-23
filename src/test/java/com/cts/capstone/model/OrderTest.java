package com.cts.capstone.model;

import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.builder.OrderBuilder;
import com.cts.capstone.builder.OrderDetailsBuilder;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

	Customer customer;
	Order order;

	@BeforeEach
	void setUp() {
		customer = CustomerBuilder.of(1234L, "Cognizant", "Bob", "street", "city", "state");
		order = OrderBuilder.of(123L, customer);
	}

	@Test
	void setOrderId() {
		order.setId(567L);
		assertEquals(567L, order.getId());
	}

	@Test
	void setDetailsList() {
		ArrayList<OrderDetails> expected = new ArrayList<>();
		expected.add(OrderDetailsBuilder.of(1, 2, 3));
		expected.add(OrderDetailsBuilder.of(1, 5, 1));
		order.setDetailsList(expected);

		assertEquals(expected, order.getDetailsList());
	}

	@Test
	void setCustomer() {
		order.setCustomer(null);
		assertNull(order.getCustomer());
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
		Order x = OrderBuilder.of(123L, customer);
		Order y = OrderBuilder.of(123L, customer);
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