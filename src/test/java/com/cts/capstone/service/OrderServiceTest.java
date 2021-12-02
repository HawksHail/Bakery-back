package com.cts.capstone.service;

import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.builder.OrderBuilder;
import com.cts.capstone.model.Customer;
import com.cts.capstone.model.Order;
import com.cts.capstone.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class OrderServiceTest {

	@Mock
	OrderRepository repository;

	Customer customer1;
	Customer customer2;
	OrderService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		service = new OrderService(repository);
		customer1 = CustomerBuilder.of(1234L);
		customer2 = CustomerBuilder.of(1235L);
	}

	@Test
	void setOrderRepository() {
		service.setOrderRepository(null);
		assertNull(service.getOrderRepository());
	}

	@Test
	void findAll() {
		List<Order> expected = new OrderBuilder()
				.w(1234L, customer1)
				.w(1235L, customer2)
				.build();
		when(repository.findAll())
				.thenReturn(expected);

		List<Order> actual = service.findAll();

		assertEquals(expected, actual);
		verify(repository, times(1)).findAll();
	}

	@Test
	void findById() {
		Order expected = OrderBuilder.of(1234L, customer1);
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
		Order expected = OrderBuilder.of(1234L, customer1);
		when(repository.save(any(Order.class)))
				.thenReturn(expected);

		Order actual = service.add(expected);

		assertEquals(expected, actual);
		verify(repository, times(1)).save(any(Order.class));
	}

	@Test
	void findByCustomerId() {
		List<Order> expected = new OrderBuilder()
				.w(1234L, customer1)
				.w(1235L, customer2)
				.build();
		when(repository.findByCustomerCustomerIdOrderByOrderDateDescIdDesc(anyLong()))
				.thenReturn(expected);

		List<Order> actual = service.findByCustomerId(customer1.getCustomerId());

		assertEquals(expected, actual);
		verify(repository, times(1)).findByCustomerCustomerIdOrderByOrderDateDescIdDesc(anyLong());
	}

	@Test
	void delete() {
		Order expected = OrderBuilder.of(1234L, customer1);
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.of(expected));

		boolean actual = service.delete(1234L);

		assertTrue(actual);
		verify(repository).findById(anyLong());
		verify(repository).deleteById(anyLong());
	}

	@Test
	void deleteNotFound() {
		Order expected = OrderBuilder.of(1234L, customer1);
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.empty());

		boolean actual = service.delete(1234L);

		assertFalse(actual);
		verify(repository).findById(anyLong());
		verify(repository, times(0)).deleteById(anyLong());
	}
}