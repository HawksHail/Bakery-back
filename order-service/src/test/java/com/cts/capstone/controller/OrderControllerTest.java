package com.cts.capstone.controller;

import com.cts.capstone.builder.OrderBuilder;
import com.cts.capstone.model.Order;
import com.cts.capstone.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

	@Mock
	OrderService service;

	OrderController controller;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		controller = new OrderController(service);
	}

	@Test
	void getAllCategories() {
		List<Order> expected = new OrderBuilder()
				.w(1234, "id123")
				.w(1235, "id124")
				.build();
		when(service.findAll())
				.thenReturn(expected);

		List<Order> actual = controller.getAllOrders();

		assertEquals(expected, actual);
		verify(service, times(1)).findAll();
	}

	@Test
	void getOrder() {
		Order expected = OrderBuilder.of(1234, "id123");
		when(service.findById(anyLong()))
				.thenReturn(expected);

		Order actual = controller.getOrder(123L);

		assertEquals(expected, actual);
		verify(service, times(1)).findById(anyLong());
	}

	@Test
	void addOrder() {
		Order expected = OrderBuilder.of(1234, "id123");
		when(service.add(any(Order.class)))
				.thenReturn(expected);

		Order actual = controller.addOrder(expected);

		assertEquals(expected, actual);
		verify(service, times(1)).add(any(Order.class));
	}

	@Test
	void getOrdersByCustomer() {
		List<Order> expected = new OrderBuilder()
				.w(1234, "id123")
				.w(1235, "id123")
				.build();
		when(service.findByCustomerId(anyString()))
				.thenReturn(expected);

		List<Order> actual = controller.getOrdersByCustomer("id123");

		assertEquals(expected, actual);
		verify(service, times(1)).findByCustomerId(anyString());
	}
}