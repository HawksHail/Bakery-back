package com.cts.capstone.controller;

import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.builder.OrderBuilder;
import com.cts.capstone.builder.OrderDetailsBuilder;
import com.cts.capstone.builder.ProductBuilder;
import com.cts.capstone.exception.ExceptionResponse;
import com.cts.capstone.exception.OrderDetailsNotFoundException;
import com.cts.capstone.model.*;
import com.cts.capstone.service.OrderDetailsService;
import com.cts.capstone.service.OrderService;
import com.cts.capstone.service.ProductService;
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
class OrderDetailsControllerTest {

	@Mock
	OrderDetailsService service;

	@Mock
	ProductService productService;

	@Mock
	OrderService orderService;

	OrderDetailsController controller;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		controller = new OrderDetailsController(service);
		controller.setOrderService(orderService);
		controller.setProductService(productService);
	}

	@Test
	void setOrderDetailsService() {
		controller.setOrderDetailsService(null);
		assertNull(controller.getOrderDetailsService());
	}

	@Test
	void setOrderService() {
		controller.setOrderService(null);
		assertNull(controller.getOrderService());
	}

	@Test
	void setProductService() {
		controller.setProductService(null);
		assertNull(controller.getProductService());
	}

	@Test
	void getAllOrderDetails() {
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
		when(service.findById(any(OrderDetailsKey.class)))
				.thenReturn(expected);

		OrderDetails actual = controller.getOrderDetailsProduct(1234L, 1234L);

		assertEquals(expected, actual);
		verify(service, times(1)).findById(any(OrderDetailsKey.class));
	}

	@Test
	void getOrderDetailsProductNotFound() {
		OrderDetails expected = OrderDetailsBuilder.of(1234, 1234, 2);
		when(service.findById(any(OrderDetailsKey.class)))
				.thenReturn(null);

		assertThrows(OrderDetailsNotFoundException.class, () -> controller.getOrderDetailsProduct(1234L, 1234L));

		verify(service, times(1)).findById(any(OrderDetailsKey.class));
	}

	@Test
	void addOrderDetails() {
		Customer customer = CustomerBuilder.of(1L);
		Order order = OrderBuilder.of(5L, customer);
		Product product1 = ProductBuilder.of(10L, "product1", "10");
		Product product2 = ProductBuilder.of(11L, "product2", "10");
		List<OrderDetails> expected = new OrderDetailsBuilder()
				.w(order, product1, 2)
				.w(order, product2, 4)
				.build();
		when(orderService.findById(5L))
				.thenReturn(order);
		when(productService.findById(10L))
				.thenReturn(product1);
		when(productService.findById(11L))
				.thenReturn(product2);
		when(service.addList(any()))
				.thenReturn(List.copyOf(expected));

		ResponseEntity<Object> actual = controller.addOrderDetailsList(expected);

		assertEquals(HttpStatus.CREATED, actual.getStatusCode());
		assertEquals(expected, actual.getBody());
		assertTrue(Objects.requireNonNull(actual.getHeaders().get("Location")).get(0).contains(String.valueOf(expected.get(0).getId().getOrderId())));
		verify(service, times(1)).addList(any());
	}

	@Test
	void addOrderDetails_OrderNotFound() {
		Customer customer = CustomerBuilder.of(1L);
		Order order = OrderBuilder.of(5L, customer);
		Product product1 = ProductBuilder.of(10L, "product1", "10");
		Product product2 = ProductBuilder.of(11L, "product2", "10");
		List<OrderDetails> expected = new OrderDetailsBuilder()
				.w(order, product1, 2)
				.w(order, product2, 4)
				.build();
		when(orderService.findById(5L))
				.thenReturn(null);
		when(productService.findById(10L))
				.thenReturn(product1);
		when(productService.findById(11L))
				.thenReturn(product2);
		when(service.addList(any()))
				.thenReturn(List.copyOf(expected));

		ResponseEntity<Object> actual = controller.addOrderDetailsList(expected);
		ExceptionResponse body = (ExceptionResponse) actual.getBody();

		assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
		assertNotNull(body);
		assertEquals("Order " + order.getId() + " not found", body.getMessage());
		verify(service, times(0)).addList(any());
	}

	@Test
	void addOrderDetails_ProductNotFound() {
		Customer customer = CustomerBuilder.of(1L);
		Order order = OrderBuilder.of(5L, customer);
		Product product1 = ProductBuilder.of(10L, "product1", "10");
		Product product2 = ProductBuilder.of(11L, "product2", "10");
		List<OrderDetails> expected = new OrderDetailsBuilder()
				.w(order, product1, 2)
				.w(order, product2, 4)
				.build();
		when(orderService.findById(5L))
				.thenReturn(order);
		when(productService.findById(10L))
				.thenReturn(null);
		when(productService.findById(11L))
				.thenReturn(product2);
		when(service.addList(any()))
				.thenReturn(List.copyOf(expected));

		ResponseEntity<Object> actual = controller.addOrderDetailsList(expected);
		ExceptionResponse body = (ExceptionResponse) actual.getBody();

		assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
		assertNotNull(body);
		assertEquals("Product " + product1.getId() + " not found", body.getMessage());
		verify(service, times(0)).addList(any());
	}

	@Test
	void addOrderDetails_noID() {
		Customer customer = CustomerBuilder.of(1L);
		Order order = OrderBuilder.of(5L, customer);
		Product product1 = ProductBuilder.of(10L, "product1", "10");
		Product product2 = ProductBuilder.of(11L, "product2", "10");
		List<OrderDetails> expected = new OrderDetailsBuilder()
				.w(order, product1, 2)
				.w(order, product2, 4)
				.build();
		expected.get(1).setId(null);
		when(orderService.findById(5L))
				.thenReturn(order);
		when(productService.findById(10L))
				.thenReturn(product1);
		when(productService.findById(11L))
				.thenReturn(product2);
		when(service.addList(any()))
				.thenReturn(List.copyOf(expected));

		ResponseEntity<Object> actual = controller.addOrderDetailsList(expected);
		ExceptionResponse body = (ExceptionResponse) actual.getBody();

		assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
		assertNotNull(body);
		assertEquals("ID was missing", body.getMessage());
		verify(service, times(0)).addList(any());
	}

	@Test
	void addOrderDetailsEmptyList() {
		List<OrderDetails> emptyList = new OrderDetailsBuilder()
				.build();

		ResponseEntity<Object> actual = controller.addOrderDetailsList(emptyList);

		assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
		assertEquals("Empty list", ((ExceptionResponse) Objects.requireNonNull(actual.getBody())).getMessage());
		verify(service, times(0)).addList(any());
	}

	@Test
	void putOrderDetails() {
		OrderDetails expected = OrderDetailsBuilder.of(1234, 1234, 2);
		when(service.add(any(OrderDetails.class)))
				.thenReturn(expected);

		ResponseEntity<OrderDetails> actual = controller.putOrderDetails(expected);

		assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
		verify(service, times(1)).add(any(OrderDetails.class));
	}

	@Test
	void deleteOrder() {
		OrderDetails expected = OrderDetailsBuilder.of(1234, 1234, 2);
		when(service.delete(any(OrderDetailsKey.class)))
				.thenReturn(true);

		ResponseEntity<OrderDetails> actual = controller.deleteOrderDetails(expected.getId().getOrderId(), expected.getId().getProductId());

		assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
		verify(service, times(1)).delete(any(OrderDetailsKey.class));
	}

	@Test
	void deleteOrderNotFound() {
		OrderDetails expected = OrderDetailsBuilder.of(1234, 1234, 2);
		when(service.delete(any(OrderDetailsKey.class)))
				.thenReturn(false);

		ResponseEntity<OrderDetails> actual = controller.deleteOrderDetails(expected.getId().getOrderId(), expected.getId().getProductId());

		assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
		verify(service, times(1)).delete(any(OrderDetailsKey.class));
	}

}