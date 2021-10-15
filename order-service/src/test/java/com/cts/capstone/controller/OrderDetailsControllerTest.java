package com.cts.capstone.controller;

import com.cts.capstone.builder.OrderDetailsBuilder;
import com.cts.capstone.model.OrderDetails;
import com.cts.capstone.service.OrderDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderDetailsControllerTest {

	@Mock
	OrderDetailsService service;

	OrderDetailsController controller;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		controller = new OrderDetailsController(service);
	}

	@Test
	void getAllCategories() {
		List<OrderDetails> expected = new OrderDetailsBuilder()
				.w(1234, 1234, 2)
				.w(1234, 1234, 4)
				.build();
		when(service.findAll())
				.thenReturn(expected);

		List<OrderDetails> actual = controller.getAllOrderDetails();

		assertEquals(expected, actual);
		verify(service, times(1)).findAll();
	}

	@Test
	void getOrderDetailsProduct() {
		OrderDetails expected = OrderDetailsBuilder.of(1234, 1234, 2);
		when(service.findByOrderIdAndProductId(anyLong(), anyLong()))
				.thenReturn(expected);

		OrderDetails actual = controller.getOrderDetailsProduct(1234L, 1234L);

		assertEquals(expected, actual);
		verify(service, times(1)).findByOrderIdAndProductId(anyLong(), anyLong());
	}

	@Test
	void addOrderDetails() {
		List<OrderDetails> expected = new OrderDetailsBuilder()
				.w(1234, 1234, 2)
				.w(1234, 1234, 4)
				.build();
		when(service.addList(any()))
				.thenReturn(expected);

		ResponseEntity<Object> actual = controller.addOrderDetailsList(expected);

		assertEquals(HttpStatus.CREATED, actual.getStatusCode());
		assertEquals(expected, actual.getBody());
		assertTrue(Objects.requireNonNull(actual.getHeaders().get("Location")).get(0).contains(String.valueOf(expected.get(0).getId().getOrderId())));
		verify(service, times(1)).addList(any());
	}
}