package com.cts.capstone.controller;

import com.cts.capstone.builder.OrderBuilder;
import com.cts.capstone.exception.OrderNotFoundException;
import com.cts.capstone.model.Order;
import com.cts.capstone.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
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
	void getOrderNotFound() {
		Order expected = OrderBuilder.of(1234, "id123");
		when(service.findById(anyLong()))
				.thenReturn(null);

		assertThrows(OrderNotFoundException.class, () -> controller.getOrder(123L));

		verify(service, times(1)).findById(anyLong());
	}

	@Test
	void addOrder() {
		Order expected = OrderBuilder.of(1234, "id123");
		when(service.add(any(Order.class)))
				.thenReturn(expected);

		ResponseEntity<Order> actual = controller.addOrder(expected);

		assertEquals(HttpStatus.CREATED, actual.getStatusCode());
		assertEquals(expected, actual.getBody());
		assertTrue(Objects.requireNonNull(actual.getHeaders().get("Location")).get(0).contains(String.valueOf(expected.getId())));
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

	@Test
	void putOrder() {
		Order expected = OrderBuilder.of(1234, "id123");
		when(service.add(any(Order.class)))
				.thenReturn(expected);

		ResponseEntity<Order> actual = controller.putOrder(expected);

		assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
		verify(service, times(1)).add(any(Order.class));
	}

	@Test
	void deleteOrder() {
		Order expected = OrderBuilder.of(1234, "id123");

		ResponseEntity<Order> actual = controller.deleteOrder(expected);

		assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
		verify(service, times(1)).delete(any(Order.class));
	}

	@Test
	void deleteOrderById() {
		ResponseEntity<Order> actual = controller.deleteOrderById(123L);

		assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
		verify(service, times(1)).delete(anyLong());
	}
}