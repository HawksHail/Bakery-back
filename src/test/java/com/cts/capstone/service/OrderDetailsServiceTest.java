package com.cts.capstone.service;

import com.cts.capstone.builder.OrderDetailsBuilder;
import com.cts.capstone.model.OrderDetails;
import com.cts.capstone.repository.OrderDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.of(expected));

		OrderDetails actual = service.findById(123L);

		assertEquals(expected, actual);
		verify(repository, times(1)).findById(anyLong());
	}

	@Test
	void findByIdNotFound() {
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.empty());

		OrderDetails actual = service.findById(123L);

		assertNull(actual);
		verify(repository, times(1)).findById(anyLong());
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
	void findByOrderIdAndProductId() {
		OrderDetails expected = OrderDetailsBuilder.of(1234, 1234, 2);
		when(repository.findByIdOrderIdAndIdProductId(anyLong(), anyLong()))
				.thenReturn(java.util.Optional.of(expected));

		OrderDetails actual = service.findByOrderIdAndProductId(expected.getId().getOrderId(), expected.getId().getProductId());

		assertEquals(expected, actual);
		verify(repository, times(1)).findByIdOrderIdAndIdProductId(anyLong(), anyLong());
	}

	@Test
	void findByOrderIdAndProductIdNotFound() {
		when(repository.findByIdOrderIdAndIdProductId(anyLong(), anyLong()))
				.thenReturn(java.util.Optional.empty());

		OrderDetails actual = service.findByOrderIdAndProductId(1234L, 1234L);

		assertNull(actual);
		verify(repository, times(1)).findByIdOrderIdAndIdProductId(anyLong(), anyLong());
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
}