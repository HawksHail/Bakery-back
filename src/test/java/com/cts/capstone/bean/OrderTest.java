package com.cts.capstone.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

	Order order;

	@BeforeEach
	void setUp() {
		order = new Order(123L, "cm1234", LocalDate.of(2021, 9, 10));
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
}