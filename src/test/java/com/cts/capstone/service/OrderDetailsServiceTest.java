package com.cts.capstone.service;

import com.cts.capstone.builder.OrderDetailsBuilder;
import com.cts.capstone.model.OrderDetails;
import com.cts.capstone.model.OrderDetailsKey;
import com.cts.capstone.repository.OrderDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class OrderDetailsServiceTest {

	@Mock
	OrderDetailsRepository repository;

	OrderDetailsService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		service = new OrderDetailsService(repository);
	}

	@Test
	void setOrderDetailsRepository() {
		service.setOrderDetailsRepository(null);
		assertNull(service.getOrderDetailsRepository());
	}

	@Test
	void findAll() {
		List<OrderDetails> expected = new OrderDetailsBuilder()
				.w(1234, 1234, 2)
				.w(1235, 1234, 4)
				.build();
		when(repository.findAll())
				.thenReturn(expected);

		List<OrderDetails> actual = service.findAll();

		assertEquals(expected, actual);
		verify(repository, times(1)).findAll();
	}

	@Test
	void findById() {
		OrderDetails expected = OrderDetailsBuilder.of(1234, 1234, 2);
		when(repository.findById(any(OrderDetailsKey.class)))
				.thenReturn(java.util.Optional.of(expected));

		OrderDetails actual = service.findById(expected.getId());

		assertEquals(expected, actual);
		verify(repository, times(1)).findById(any(OrderDetailsKey.class));
	}

	@Test
	void findByIdNotFound() {
		when(repository.findById(any(OrderDetailsKey.class)))
				.thenReturn(java.util.Optional.empty());

		OrderDetails actual = service.findById(new OrderDetailsKey(1, 1));

		assertNull(actual);
		verify(repository, times(1)).findById(any(OrderDetailsKey.class));
	}

	@Test
	void add() {
		OrderDetails expected = OrderDetailsBuilder.of(1234, 1234, 2);
		when(repository.save(any(OrderDetails.class)))
				.thenReturn(expected);

		OrderDetails actual = service.add(expected);

		assertEquals(expected, actual);
		verify(repository, times(1)).save(any(OrderDetails.class));
	}

	@Test
	void addList() {
		List<OrderDetails> expected = new OrderDetailsBuilder()
				.w(1234, 1234, 2)
				.w(1235, 1234, 4)
				.build();
		when(repository.saveAll(ArgumentMatchers.<List<OrderDetails>>any()))
				.thenReturn(expected);

		List<OrderDetails> actual = service.addList(expected);

		assertEquals(expected, actual);
		verify(repository, times(1)).saveAll(ArgumentMatchers.<List<OrderDetails>>any());
	}

	@Test
	void findAllById() {
		List<OrderDetails> expected = new OrderDetailsBuilder()
				.w(1234, 1234, 2)
				.w(1234, 1234, 4)
				.build();
		when(repository.findAllByIdOrderId(anyLong()))
				.thenReturn(expected);

		List<OrderDetails> actual = service.findAllById(1234L);

		assertEquals(expected, actual);
		verify(repository, times(1)).findAllByIdOrderId(anyLong());
	}

	@Test
	void delete() {
		OrderDetails expected = OrderDetailsBuilder.of(1234, 1234, 2);
		when(repository.findById(any(OrderDetailsKey.class)))
				.thenReturn(java.util.Optional.of(expected));

		boolean actual = service.delete(expected.getId());

		assertTrue(actual);
		verify(repository).findById(any(OrderDetailsKey.class));
		verify(repository).deleteById(any(OrderDetailsKey.class));
	}

	@Test
	void deleteNotFound() {
		OrderDetails expected = OrderDetailsBuilder.of(1234, 1234, 2);
		when(repository.findById(any(OrderDetailsKey.class)))
				.thenReturn(java.util.Optional.empty());

		boolean actual = service.delete(expected.getId());

		assertFalse(actual);
		verify(repository).findById(any(OrderDetailsKey.class));
		verify(repository, times(0)).deleteById(any(OrderDetailsKey.class));
	}
}