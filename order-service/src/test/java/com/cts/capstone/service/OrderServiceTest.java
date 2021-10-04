package com.cts.capstone.service;

import com.cts.capstone.builder.OrderBuilder;
import com.cts.capstone.model.Order;
import com.cts.capstone.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


class OrderServiceTest {

	@Mock
	OrderRepository repository;

	OrderService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		service = new OrderService(repository);
	}

	@Test
	void findAll() {
		List<Order> expected = new OrderBuilder()
				.w(1234, "id123")
				.w(1235, "id124")
				.build();
		when(repository.findAll())
				.thenReturn(expected);

		List<Order> actual = service.findAll();

		assertEquals(expected, actual);
		verify(repository, times(1)).findAll();
	}

	@Test
	void findById() {
		Order expected = OrderBuilder.of(1234, "id123");
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.of(expected));

		Order actual = service.findById(123L);

		assertEquals(expected, actual);
		verify(repository, times(1)).findById(anyLong());
	}

	@Test
	void findByIdNotFound() {
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.empty());

		Order actual = service.findById(123L);

		assertNull(actual);
		verify(repository, times(1)).findById(anyLong());
	}

	@Test
	void add() {
		Order expected = OrderBuilder.of(1234, "id123");
		when(repository.save(any(Order.class)))
				.thenReturn(expected);

		Order actual = service.add(expected);

		assertEquals(expected, actual);
		verify(repository, times(1)).save(any(Order.class));
	}

	@Test
	void findByCustomerId() {
		List<Order> expected = new OrderBuilder()
				.w(1234, "id123")
				.w(1235, "id124")
				.build();
		when(repository.findByCustomerId(anyString()))
				.thenReturn(expected);

		List<Order> actual = service.findByCustomerId("id123");

		assertEquals(expected, actual);
		verify(repository, times(1)).findByCustomerId(anyString());
	}
}